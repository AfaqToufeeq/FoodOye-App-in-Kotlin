// Generated by view binder compiler. Do not edit!
package com.app.foodoyeadmin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoyeadmin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOrdersBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView Recyclerview;

  @NonNull
  public final ImageView backButton;

  @NonNull
  public final ImageView icEmptyList;

  @NonNull
  public final ImageView ivToogle;

  @NonNull
  public final ConstraintLayout layoutEmptyList;

  @NonNull
  public final ConstraintLayout layoutToolBar;

  @NonNull
  public final TextView tvTitleSpecific;

  private FragmentOrdersBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView Recyclerview, @NonNull ImageView backButton,
      @NonNull ImageView icEmptyList, @NonNull ImageView ivToogle,
      @NonNull ConstraintLayout layoutEmptyList, @NonNull ConstraintLayout layoutToolBar,
      @NonNull TextView tvTitleSpecific) {
    this.rootView = rootView;
    this.Recyclerview = Recyclerview;
    this.backButton = backButton;
    this.icEmptyList = icEmptyList;
    this.ivToogle = ivToogle;
    this.layoutEmptyList = layoutEmptyList;
    this.layoutToolBar = layoutToolBar;
    this.tvTitleSpecific = tvTitleSpecific;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOrdersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOrdersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_orders, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOrdersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Recyclerview;
      RecyclerView Recyclerview = ViewBindings.findChildViewById(rootView, id);
      if (Recyclerview == null) {
        break missingId;
      }

      id = R.id.back_Button;
      ImageView backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.ic_empty_list;
      ImageView icEmptyList = ViewBindings.findChildViewById(rootView, id);
      if (icEmptyList == null) {
        break missingId;
      }

      id = R.id.iv_toogle;
      ImageView ivToogle = ViewBindings.findChildViewById(rootView, id);
      if (ivToogle == null) {
        break missingId;
      }

      id = R.id.layout_emptyList;
      ConstraintLayout layoutEmptyList = ViewBindings.findChildViewById(rootView, id);
      if (layoutEmptyList == null) {
        break missingId;
      }

      id = R.id.layout_toolBar;
      ConstraintLayout layoutToolBar = ViewBindings.findChildViewById(rootView, id);
      if (layoutToolBar == null) {
        break missingId;
      }

      id = R.id.tv_title_specific;
      TextView tvTitleSpecific = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleSpecific == null) {
        break missingId;
      }

      return new FragmentOrdersBinding((ConstraintLayout) rootView, Recyclerview, backButton,
          icEmptyList, ivToogle, layoutEmptyList, layoutToolBar, tvTitleSpecific);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
