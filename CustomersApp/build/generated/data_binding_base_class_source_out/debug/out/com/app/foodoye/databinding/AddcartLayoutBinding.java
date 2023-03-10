// Generated by view binder compiler. Do not edit!
package com.app.foodoye.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoye.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AddcartLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView deleteItemIV;

  @NonNull
  public final CircleImageView foodImage;

  @NonNull
  public final TextView foodPrice;

  @NonNull
  public final TextView foodTitle;

  @NonNull
  public final CardView layoutCardIcon;

  @NonNull
  public final LinearLayout layoutContent;

  @NonNull
  public final AppCompatButton less;

  @NonNull
  public final AppCompatButton more;

  @NonNull
  public final TextView number;

  private AddcartLayoutBinding(@NonNull CardView rootView, @NonNull ImageView deleteItemIV,
      @NonNull CircleImageView foodImage, @NonNull TextView foodPrice, @NonNull TextView foodTitle,
      @NonNull CardView layoutCardIcon, @NonNull LinearLayout layoutContent,
      @NonNull AppCompatButton less, @NonNull AppCompatButton more, @NonNull TextView number) {
    this.rootView = rootView;
    this.deleteItemIV = deleteItemIV;
    this.foodImage = foodImage;
    this.foodPrice = foodPrice;
    this.foodTitle = foodTitle;
    this.layoutCardIcon = layoutCardIcon;
    this.layoutContent = layoutContent;
    this.less = less;
    this.more = more;
    this.number = number;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static AddcartLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AddcartLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.addcart_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AddcartLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deleteItem_IV;
      ImageView deleteItemIV = ViewBindings.findChildViewById(rootView, id);
      if (deleteItemIV == null) {
        break missingId;
      }

      id = R.id.foodImage;
      CircleImageView foodImage = ViewBindings.findChildViewById(rootView, id);
      if (foodImage == null) {
        break missingId;
      }

      id = R.id.food_price;
      TextView foodPrice = ViewBindings.findChildViewById(rootView, id);
      if (foodPrice == null) {
        break missingId;
      }

      id = R.id.food_title;
      TextView foodTitle = ViewBindings.findChildViewById(rootView, id);
      if (foodTitle == null) {
        break missingId;
      }

      id = R.id.layout_cardIcon;
      CardView layoutCardIcon = ViewBindings.findChildViewById(rootView, id);
      if (layoutCardIcon == null) {
        break missingId;
      }

      id = R.id.layoutContent;
      LinearLayout layoutContent = ViewBindings.findChildViewById(rootView, id);
      if (layoutContent == null) {
        break missingId;
      }

      id = R.id.less;
      AppCompatButton less = ViewBindings.findChildViewById(rootView, id);
      if (less == null) {
        break missingId;
      }

      id = R.id.more;
      AppCompatButton more = ViewBindings.findChildViewById(rootView, id);
      if (more == null) {
        break missingId;
      }

      id = R.id.number;
      TextView number = ViewBindings.findChildViewById(rootView, id);
      if (number == null) {
        break missingId;
      }

      return new AddcartLayoutBinding((CardView) rootView, deleteItemIV, foodImage, foodPrice,
          foodTitle, layoutCardIcon, layoutContent, less, more, number);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
