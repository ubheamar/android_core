package in.co.webstersys.core.ui.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import javax.inject.Inject;

import in.co.webstersys.core.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class CoreActivity<DB extends ViewDataBinding,VM extends ViewModel> extends AppCompatActivity {
   @LayoutRes
   protected abstract int getLayoutRes();
   @Inject
   protected ViewModelProvider.Factory viewModelFactory;
   public abstract Class<VM> getViewModel();
   protected DB dataBinding;
   protected VM viewModel;
   protected Toolbar toolbar;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
      viewModel = ViewModelProviders.of(this,viewModelFactory).get(getViewModel());
      setupToolbar();
   }

   private void setupToolbar() {
      toolbar = (Toolbar)findViewById(R.id.toolbar);
      if(toolbar!=null)
         setSupportActionBar(toolbar);
   }
   protected void showToast(@Nullable String message) {
      if(message!=null)
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
   }
   protected void showLongToast(@Nullable String message) {
      if(message!=null)
         Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }

   @Override
   protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
   }
}
