package pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> navigator;

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }
}
