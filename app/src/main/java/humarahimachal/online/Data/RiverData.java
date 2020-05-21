package humarahimachal.online.Data;

import android.content.Context;

import java.util.ArrayList;

import humarahimachal.online.Modal.RiverModal;
import humarahimachal.online.R;

public class RiverData {

    public static ArrayList<RiverModal> getRiverData(Context context) {
        ArrayList<RiverModal> riverModals = new ArrayList<>();
        riverModals.add(new RiverModal(
                context.getResources().getString(R.string.satlujhpEntry),
                context.getResources().getString(R.string.satlujName),
                context.getResources().getString(R.string.satlujv),
                context.getResources().getString(R.string.satlujS),
                context.getResources().getString(R.string.satlujLen),
                context.getResources().getString(R.string.satlujorginPlace),
                context.getResources().getString(R.string.satlujhpExit),
                context.getResources().getString(R.string.satlujF),
                context.getResources().getString(R.string.satlujTr),
                context.getResources().getString(R.string.satlujCatch)));
        riverModals.add(new RiverModal(
                context.getResources().getString(R.string.BOHP),
                context.getResources().getString(R.string.BName),
                context.getResources().getString(R.string.BV),
                context.getResources().getString(R.string.BS),
                context.getResources().getString(R.string.BLHP),
                context.getResources().getString(R.string.BOP),
                context.getResources().getString(R.string.BEHP),
                context.getResources().getString(R.string.BMHP),
                context.getResources().getString(R.string.BTHP),
                context.getResources().getString(R.string.BCHP)
        ));
        riverModals.add(new RiverModal(
                context.getResources().getString(R.string.ROHP),
                context.getResources().getString(R.string.RNnam),
                context.getResources().getString(R.string.RV),
                context.getResources().getString(R.string.RS),
                context.getResources().getString(R.string.RLHP),
                context.getResources().getString(R.string.ROP),
                context.getResources().getString(R.string.REHP),
                context.getResources().getString(R.string.RMhp),
                context.getResources().getString(R.string.RThp),
                context.getResources().getString(R.string.Rchp)
        ));
        riverModals.add(new RiverModal(
                context.getResources().getString(R.string.COHP),
                context.getResources().getString(R.string.CName),
                context.getResources().getString(R.string.CV),
                context.getResources().getString(R.string.CS),
                context.getResources().getString(R.string.CLhp),
                context.getResources().getString(R.string.COP),
                context.getResources().getString(R.string.CEhp),
                context.getResources().getString(R.string.CMhp),
                context.getResources().getString(R.string.CThp),
                context.getResources().getString(R.string.CChp)
        ));
        riverModals.add(new RiverModal(
                context.getResources().getString(R.string.YOHP),
                context.getResources().getString(R.string.YName),
                context.getResources().getString(R.string.YV),
                context.getResources().getString(R.string.YS),
                context.getResources().getString(R.string.YLhp),
                context.getResources().getString(R.string.YOP),
                context.getResources().getString(R.string.YEhp),
                context.getResources().getString(R.string.YMhp),
                context.getResources().getString(R.string.YThp),
                context.getResources().getString(R.string.YChp)
        ));
        return riverModals;
    }
}
