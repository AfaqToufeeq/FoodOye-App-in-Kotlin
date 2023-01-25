// Generated by view binder compiler. Do not edit!
package com.app.foodoye.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoye.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MyordersLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView TotalBillTV;

  @NonNull
  public final CardView cartOrderDetails;

  @NonNull
  public final TextView foodNameTV;

  @NonNull
  public final TextView orderNumberTV;

  @NonNull
  public final TextView quantityTVV;

  @NonNull
  public final TextView statusTV;

  private MyordersLayoutBinding(@NonNull CardView rootView, @NonNull TextView TotalBillTV,
      @NonNull CardView cartOrderDetails, @NonNull TextView foodNameTV,
      @NonNull TextView orderNumberTV, @NonNull TextView quantityTVV, @NonNull TextView statusTV) {
    this.rootView = rootView;
    this.TotalBillTV = TotalBillTV;
    this.cartOrderDetails = cartOrderDetails;
    this.foodNameTV = foodNameTV;
    this.orderNumberTV = orderNumberTV;
    this.quantityTVV = quantityTVV;
    this.statusTV = statusTV;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static MyordersLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MyordersLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.myorders_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MyordersLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TotalBillTV;
      TextView TotalBillTV = ViewBindings.findChildViewById(rootView, id);
      if (TotalBillTV == null) {
        break missingId;
      }

      id = R.id.cartOrderDetails;
      CardView cartOrderDetails = ViewBindings.findChildViewById(rootView, id);
      if (cartOrderDetails == null) {
        break missingId;
      }

      id = R.id.foodNameTV;
      TextView foodNameTV = ViewBindings.findChildViewById(rootView, id);
      if (foodNameTV == null) {
        break missingId;
      }

      id = R.id.orderNumberTV;
      TextView orderNumberTV = ViewBindings.findChildViewById(rootView, id);
      if (orderNumberTV == null) {
        break missingId;
      }

      id = R.id.quantityTVV;
      TextView quantityTVV = ViewBindings.findChildViewById(rootView, id);
      if (quantityTVV == null) {
        break missingId;
      }

      id = R.id.statusTV;
      TextView statusTV = ViewBindings.findChildViewById(rootView, id);
      if (statusTV == null) {
        break missingId;
      }

      return new MyordersLayoutBinding((CardView) rootView, TotalBillTV, cartOrderDetails,
          foodNameTV, orderNumberTV, quantityTVV, statusTV);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
