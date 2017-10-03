package ch.hsr.mge.gadgeothek.ui.loans;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.domain.Loan;

public class LoansRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;

    private final List<Loan> loans;

    public LoansRecyclerAdapter(List<Loan> loans) {
        this.loans = loans;
    }

    /**
     * To show a placeholder when the list is empty, we return the view type VIEW_TYPE_EMPTY_LIST_PLACEHOLDER.
     * In onCreateViewHolder, this is used to return a EmptyRecyclerItemViewHolder instance. Note that for this
     * to work, we also need to make sure the getItemCount returns at least 1, otherwise no placeholder is shown.
     */
    @Override
    public int getItemViewType(int position) {
        if (loans.isEmpty()) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_EMPTY_LIST_PLACEHOLDER) {
            View view = layoutInflater.inflate(R.layout.recycler_empty_item, parent, false);
            return new EmptyRecyclerItemViewHolder(view);
        }

        // TODO create a ViewHolder here

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;

        if (holder.isEmptyPlaceholder()) {
            return;
        }


        // TODO bind the ViewHolder here
    }

    public int getItemCount() {
        /* To show the empty view placeholder, we always need to return at least 1 */
        return loans.isEmpty() ? 1 : loans.size();
    }

    public static class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

        // TODO add all missing attributes here

        public RecyclerItemViewHolder(View parent) {
            super(parent);
        }

        public boolean isEmptyPlaceholder() {
            return false;
        }
    }

    public static class EmptyRecyclerItemViewHolder extends RecyclerItemViewHolder {

        public EmptyRecyclerItemViewHolder(View parent) {
            super(parent);
        }

        public boolean isEmptyPlaceholder() {
            return true;
        }
    }
}
