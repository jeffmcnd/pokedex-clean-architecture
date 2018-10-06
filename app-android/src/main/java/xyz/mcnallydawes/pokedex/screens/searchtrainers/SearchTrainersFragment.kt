package xyz.mcnallydawes.pokedex.screens.searchtrainers

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
import kotlinx.android.synthetic.main.fragment_search_trainers.*
import org.koin.android.ext.android.inject
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.adapter.TrainerAdapter
import xyz.mcnallydawes.pokedex.common.constants.Extras
import xyz.mcnallydawes.pokedex.common.inflater.TrainerInflater
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver
import java.util.concurrent.TimeUnit

class SearchTrainersFragment: Fragment() {

    companion object {
        fun newInstance(): SearchTrainersFragment = SearchTrainersFragment()
    }

    private val vm: SearchTrainersViewModel by inject()

    private val trainerAdapter: TrainerAdapter by lazy {
        val inflater = TrainerInflater(LayoutInflater.from(requireContext()))
        TrainerAdapter(inflater) { trainer ->
            // TODO: Launch trainer detail activity
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_trainers, container, false)

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        setUpViews()
        setUpObservers()
        setUpViewModel(savedState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(Extras.IS_VIEW_MODEL_INITIALIZED, true)
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        setUpTrainersRecyclerView()
        setUpClearBtn()
        setUpSearchEditText()
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
        vm.error.observe(this, NonNullObserver(handleError))
    }

    private fun setUpTrainersRecyclerView() {
        trainersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        trainersRecyclerView.adapter = trainerAdapter
    }

    private fun setUpClearBtn() {
        clearBtn.visibility = View.GONE
        clearBtn.setOnClickListener {
            clearBtn.visibility = View.GONE
            searchEditText.setText("", TextView.BufferType.EDITABLE)
            vm.load()
        }
    }

    private fun setUpSearchEditText() {
        RxTextView.afterTextChangeEvents(searchEditText)
                .skip(1)
                .doOnNext(updateClearBtnVisibility)
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(handleQuery, {})
    }

    private fun setUpViewModel(savedState: Bundle?) {
        val isInitialized = savedState?.getBoolean(Extras.IS_VIEW_MODEL_INITIALIZED) ?: false
        if (!isInitialized) {
            vm.load()
        }
    }

    private val updateClearBtnVisibility: (TextViewAfterTextChangeEvent) -> Unit = { event ->
        val query = event.view().text
        clearBtn.visibility = if (query.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private val handleQuery: (TextViewAfterTextChangeEvent) -> Unit = { event ->
        val query = event.view().text.toString()
        if (query.isEmpty()) {
            vm.load()
        } else if (query.length >= 2) {
            vm.search(searchEditText.text.toString())
        }
    }

    private val render: (SearchTrainersModel) -> Unit = { model ->
        trainerAdapter.replace(model.trainers)
    }

    private val handleError: (Throwable) -> Unit = { throwable ->
        Snackbar.make(layout, throwable.message ?: "An error occurred", Snackbar.LENGTH_LONG).show()
    }

}