package ch.hsr.mge.gadgeothek.ui.loans;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Loan;
import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class LoansFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<Loan> loans = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private LoansRecyclerAdapter loansRecyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.loans_fragment, container, false);

        // TODO assign recyclerView attribute

        refreshLayout = (SwipeRefreshLayout) fragment.findViewById(R.id.swipeLayout);
        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        refreshLayout.setOnRefreshListener(this);

        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (LibraryService.isLoggedIn()) {
            refreshLoans();
        } else {
            snack("You are not logged in!?");
        }
    }

    /**
     * This is called when the user refreshes the view by dragging down the recycler view
     */
    @Override
    public void onRefresh() {
        refreshLoans();
    }

    private void refreshLoans() {
        // To show the loading indicator, we set refreshing to true. Once the data has been loaded
        // and the adapter notified about the changes, it needs to be set back to false.
        refreshLayout.setRefreshing(true);

        // TODO call LibraryService.getLoansForCustomer to get a List<Loan>. Add the result
        // to the loans attribute and create/update the adapter.
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> newLoans) {


                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(String message) {


                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void snack(String msg) {
        final Snackbar snackbar = Snackbar.make(refreshLayout, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss_snackbar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }

}
