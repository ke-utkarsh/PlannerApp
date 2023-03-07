// Generated by data binding compiler. Do not edit!
package ymsli.com.adminemployee.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.adminemployee.R;

public abstract class DialogDeleteBinding extends ViewDataBinding {
  @NonNull
  public final TextView tvCancel;

  @NonNull
  public final TextView tvSubmit;

  protected DialogDeleteBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView tvCancel, TextView tvSubmit) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tvCancel = tvCancel;
    this.tvSubmit = tvSubmit;
  }

  @NonNull
  public static DialogDeleteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_delete, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DialogDeleteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DialogDeleteBinding>inflateInternal(inflater, R.layout.dialog_delete, root, attachToRoot, component);
  }

  @NonNull
  public static DialogDeleteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_delete, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DialogDeleteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DialogDeleteBinding>inflateInternal(inflater, R.layout.dialog_delete, null, false, component);
  }

  public static DialogDeleteBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static DialogDeleteBinding bind(@NonNull View view, @Nullable Object component) {
    return (DialogDeleteBinding)bind(component, view, R.layout.dialog_delete);
  }
}
