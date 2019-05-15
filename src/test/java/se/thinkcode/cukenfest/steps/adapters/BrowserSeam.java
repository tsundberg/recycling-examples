package se.thinkcode.cukenfest.steps.adapters;

import java.util.Objects;

class BrowserSeam {
    private String browser;

    BrowserSeam(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowserSeam seam = (BrowserSeam) o;
        return Objects.equals(browser, seam.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(browser);
    }

    @Override
    public String toString() {
        return browser;
    }
}
