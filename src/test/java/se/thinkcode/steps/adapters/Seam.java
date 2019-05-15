package se.thinkcode.steps.adapters;

import java.util.Objects;

public class Seam {
    private String seam;

    Seam(String seam) {
        this.seam = seam;
    }

    public String getSeam() {
        return seam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seam seam1 = (Seam) o;
        return Objects.equals(seam, seam1.seam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seam);
    }

    @Override
    public String toString() {
        return seam;
    }
}
