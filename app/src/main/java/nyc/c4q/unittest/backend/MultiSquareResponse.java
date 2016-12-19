package nyc.c4q.unittest.backend;

import android.graphics.Color;

public class MultiSquareResponse {
    private final int mBox1;
    private final int mBox2;
    private final int mBox3;
    private final int mBox4;

    public MultiSquareResponse(String box1, String box2, String box3, String box4) {
        this.mBox1 = Color.parseColor(box1);
        this.mBox2 = Color.parseColor(box2);
        this.mBox3 = Color.parseColor(box3);
        this.mBox4 = Color.parseColor(box4);
    }

    public int getFirstColor() {
        return mBox1;
    }

    public int getSecondColor() {
        return mBox2;
    }

    public int getThirdColor() {
        return mBox3;
    }

    public int getFourthColor() {
        return mBox4;
    }
}
