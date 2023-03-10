// Generated by view binder compiler. Do not edit!
package com.app.foodoye.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.foodoye.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText emailad;

  @NonNull
  public final TextView forgetpass;

  @NonNull
  public final EditText ivPass;

  @NonNull
  public final CircleImageView ivWhatsapp;

  @NonNull
  public final AppCompatButton loginBtn;

  @NonNull
  public final AppCompatButton signup;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  private ActivityLoginBinding(@NonNull LinearLayout rootView, @NonNull EditText emailad,
      @NonNull TextView forgetpass, @NonNull EditText ivPass, @NonNull CircleImageView ivWhatsapp,
      @NonNull AppCompatButton loginBtn, @NonNull AppCompatButton signup,
      @NonNull TextView textView, @NonNull TextView textView2) {
    this.rootView = rootView;
    this.emailad = emailad;
    this.forgetpass = forgetpass;
    this.ivPass = ivPass;
    this.ivWhatsapp = ivWhatsapp;
    this.loginBtn = loginBtn;
    this.signup = signup;
    this.textView = textView;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailad;
      EditText emailad = ViewBindings.findChildViewById(rootView, id);
      if (emailad == null) {
        break missingId;
      }

      id = R.id.forgetpass;
      TextView forgetpass = ViewBindings.findChildViewById(rootView, id);
      if (forgetpass == null) {
        break missingId;
      }

      id = R.id.iv_pass;
      EditText ivPass = ViewBindings.findChildViewById(rootView, id);
      if (ivPass == null) {
        break missingId;
      }

      id = R.id.iv_whatsapp;
      CircleImageView ivWhatsapp = ViewBindings.findChildViewById(rootView, id);
      if (ivWhatsapp == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      AppCompatButton loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      id = R.id.signup;
      AppCompatButton signup = ViewBindings.findChildViewById(rootView, id);
      if (signup == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivityLoginBinding((LinearLayout) rootView, emailad, forgetpass, ivPass,
          ivWhatsapp, loginBtn, signup, textView, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
