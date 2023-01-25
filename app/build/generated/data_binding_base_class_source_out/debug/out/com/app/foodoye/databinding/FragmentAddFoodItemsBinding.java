// Generated by view binder compiler. Do not edit!
package com.app.foodoye.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoye.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAddFoodItemsBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final EditText FoodDescription;

  @NonNull
  public final ImageView FoodImage;

  @NonNull
  public final EditText FoodName;

  @NonNull
  public final EditText FoodPrice;

  @NonNull
  public final AppCompatButton addToCartBtn;

  @NonNull
  public final CardView parentCardView;

  private FragmentAddFoodItemsBinding(@NonNull FrameLayout rootView,
      @NonNull EditText FoodDescription, @NonNull ImageView FoodImage, @NonNull EditText FoodName,
      @NonNull EditText FoodPrice, @NonNull AppCompatButton addToCartBtn,
      @NonNull CardView parentCardView) {
    this.rootView = rootView;
    this.FoodDescription = FoodDescription;
    this.FoodImage = FoodImage;
    this.FoodName = FoodName;
    this.FoodPrice = FoodPrice;
    this.addToCartBtn = addToCartBtn;
    this.parentCardView = parentCardView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAddFoodItemsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAddFoodItemsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_add_food_items, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAddFoodItemsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.FoodDescription;
      EditText FoodDescription = ViewBindings.findChildViewById(rootView, id);
      if (FoodDescription == null) {
        break missingId;
      }

      id = R.id.FoodImage;
      ImageView FoodImage = ViewBindings.findChildViewById(rootView, id);
      if (FoodImage == null) {
        break missingId;
      }

      id = R.id.FoodName;
      EditText FoodName = ViewBindings.findChildViewById(rootView, id);
      if (FoodName == null) {
        break missingId;
      }

      id = R.id.FoodPrice;
      EditText FoodPrice = ViewBindings.findChildViewById(rootView, id);
      if (FoodPrice == null) {
        break missingId;
      }

      id = R.id.addToCartBtn;
      AppCompatButton addToCartBtn = ViewBindings.findChildViewById(rootView, id);
      if (addToCartBtn == null) {
        break missingId;
      }

      id = R.id.parentCardView;
      CardView parentCardView = ViewBindings.findChildViewById(rootView, id);
      if (parentCardView == null) {
        break missingId;
      }

      return new FragmentAddFoodItemsBinding((FrameLayout) rootView, FoodDescription, FoodImage,
          FoodName, FoodPrice, addToCartBtn, parentCardView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
