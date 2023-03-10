// Generated by view binder compiler. Do not edit!
package com.app.foodoye.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoye.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFoodMenuBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton CartfloatButton;

  @NonNull
  public final ImageView backButton;

  @NonNull
  public final RecyclerView foodMenuRecyclerview;

  @NonNull
  public final ImageView icEmptyList;

  @NonNull
  public final ConstraintLayout layoutEmptyList;

  @NonNull
  public final ConstraintLayout layoutToolBar;

  @NonNull
  public final AppCompatButton orderCartButton;

  @NonNull
  public final ImageView trashDelete;

  @NonNull
  public final TextView tvTitleSpecific;

  private FragmentFoodMenuBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton CartfloatButton, @NonNull ImageView backButton,
      @NonNull RecyclerView foodMenuRecyclerview, @NonNull ImageView icEmptyList,
      @NonNull ConstraintLayout layoutEmptyList, @NonNull ConstraintLayout layoutToolBar,
      @NonNull AppCompatButton orderCartButton, @NonNull ImageView trashDelete,
      @NonNull TextView tvTitleSpecific) {
    this.rootView = rootView;
    this.CartfloatButton = CartfloatButton;
    this.backButton = backButton;
    this.foodMenuRecyclerview = foodMenuRecyclerview;
    this.icEmptyList = icEmptyList;
    this.layoutEmptyList = layoutEmptyList;
    this.layoutToolBar = layoutToolBar;
    this.orderCartButton = orderCartButton;
    this.trashDelete = trashDelete;
    this.tvTitleSpecific = tvTitleSpecific;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFoodMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFoodMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_food_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFoodMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CartfloatButton;
      FloatingActionButton CartfloatButton = ViewBindings.findChildViewById(rootView, id);
      if (CartfloatButton == null) {
        break missingId;
      }

      id = R.id.back_Button;
      ImageView backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.foodMenuRecyclerview;
      RecyclerView foodMenuRecyclerview = ViewBindings.findChildViewById(rootView, id);
      if (foodMenuRecyclerview == null) {
        break missingId;
      }

      id = R.id.ic_empty_list;
      ImageView icEmptyList = ViewBindings.findChildViewById(rootView, id);
      if (icEmptyList == null) {
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

      id = R.id.orderCartButton;
      AppCompatButton orderCartButton = ViewBindings.findChildViewById(rootView, id);
      if (orderCartButton == null) {
        break missingId;
      }

      id = R.id.trashDelete;
      ImageView trashDelete = ViewBindings.findChildViewById(rootView, id);
      if (trashDelete == null) {
        break missingId;
      }

      id = R.id.tv_title_specific;
      TextView tvTitleSpecific = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleSpecific == null) {
        break missingId;
      }

      return new FragmentFoodMenuBinding((ConstraintLayout) rootView, CartfloatButton, backButton,
          foodMenuRecyclerview, icEmptyList, layoutEmptyList, layoutToolBar, orderCartButton,
          trashDelete, tvTitleSpecific);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
