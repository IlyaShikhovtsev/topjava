package ru.javawebinar.topjava.util;

public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
