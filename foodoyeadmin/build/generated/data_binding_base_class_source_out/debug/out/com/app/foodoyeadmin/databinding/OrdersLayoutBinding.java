// Generated by view binder compiler. Do not edit!
package com.app.foodoyeadmin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoyeadmin.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class OrdersLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView TotalBillTV;

  @NonNull
  public final CardView cartOrderDetails;

  @NonNull
  public final TextView customerAddressTV;

  @NonNull
  public final TextView customerCommentsTV;

  @NonNull
  public final TextView customerNameTV;

  @NonNull
  public final TextView customerPhoneTV;

  @NonNull
  public final TextView dateTV;

  @NonNull
  public final TextView foodNameTV;

  @NonNull
  public final TextView orderNumberTV;

  @NonNull
  public final TextView quantityTVV;

  @NonNull
  public final TextView statusTV;

  @NonNull
  public final TextView timeTV;

  private OrdersLayoutBinding(@NonNull CardView rootView, @NonNull TextView TotalBillTV,
      @NonNull CardView cartOrderDetails, @NonNull TextView customerAddressTV,
      @NonNull TextView customerCommentsTV, @NonNull TextView customerNameTV,
      @NonNull TextView customerPhoneTV, @NonNull TextView dateTV, @NonNull TextView foodNameTV,
      @NonNull TextView orderNumberTV, @NonNull TextView quantityTVV, @NonNull TextView statusTV,
      @NonNull TextView timeTV) {
    this.rootView = rootView;
    this.TotalBillTV = TotalBillTV;
    this.cartOrderDetails = cartOrderDetails;
    this.customerAddressTV = customerAddressTV;
    this.customerCommentsTV = customerCommentsTV;
    this.customerNameTV = customerNameTV;
    this.customerPhoneTV = customerPhoneTV;
    this.dateTV = dateTV;
    this.foodNameTV = foodNameTV;
    this.orderNumberTV = orderNumberTV;
    this.quantityTVV = quantityTVV;
    this.statusTV = statusTV;
    this.timeTV = timeTV;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static OrdersLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OrdersLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.orders_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OrdersLayoutBinding bind(@NonNull View rootView) {
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

      id = R.id.customerAddressTV;
      TextView customerAddressTV = ViewBindings.findChildViewById(rootView, id);
      if (customerAddressTV == null) {
        break missingId;
      }

      id = R.id.customerCommentsTV;
      TextView customerCommentsTV = ViewBindings.findChildViewById(rootView, id);
      if (customerCommentsTV == null) {
        break missingId;
      }

      id = R.id.customerNameTV;
      TextView customerNameTV = ViewBindings.findChildViewById(rootView, id);
      if (customerNameTV == null) {
        break missingId;
      }

      id = R.id.customerPhoneTV;
      TextView customerPhoneTV = ViewBindings.findChildViewById(rootView, id);
      if (customerPhoneTV == null) {
        break missingId;
      }

      id = R.id.dateTV;
      TextView dateTV = ViewBindings.findChildViewById(rootView, id);
      if (dateTV == null) {
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

      id = R.id.timeTV;
      TextView timeTV = ViewBindings.findChildViewById(rootView, id);
      if (timeTV == null) {
        break missingId;
      }

      return new OrdersLayoutBinding((CardView) rootView, TotalBillTV, cartOrderDetails,
          customerAddressTV, customerCommentsTV, customerNameTV, customerPhoneTV, dateTV,
          foodNameTV, orderNumberTV, quantityTVV, statusTV, timeTV);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
