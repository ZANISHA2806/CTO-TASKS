package sample;

import java.util.ArrayList;
import java.util.List;

import model.ShareholderRecord;
public class Data {

    public static List<ShareholderRecord> getSampleShareholders() {

        List<ShareholderRecord> shareholders = new ArrayList<>();

        shareholders.add(new ShareholderRecord("S01", 500, true, true, false));

        shareholders.add(new ShareholderRecord("S02", 0, false, true, false));

        shareholders.add(new ShareholderRecord("S03", 200, true, false, false));

        shareholders.add(new ShareholderRecord("S04", 0.4, true, true, false));

        shareholders.add(new ShareholderRecord("S05", 300, true, true, true));

        shareholders.add(new ShareholderRecord("S06", 100, true, true, false));
        shareholders.add(new ShareholderRecord("S07", 150, true, true, false));
        shareholders.add(new ShareholderRecord("S08", 200, true, true, false));
        shareholders.add(new ShareholderRecord("S09", 250, true, true, false));
        shareholders.add(new ShareholderRecord("S10", 300, true, true, false));

        return shareholders;
    }
}