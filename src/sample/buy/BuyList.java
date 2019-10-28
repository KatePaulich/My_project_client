package sample.buy;

import java.util.ArrayList;
import java.util.List;
/**
 * BuyList class for storing buyList information
 */
public class BuyList {
    /**
     * buyList of user list
     */
    private List<BuyYou> buyList;

    /**
     * This method forms buyList
     */
    public BuyList(){
        buyList = new ArrayList<>();
    }

    /**
     * This method to add BuyYous to the buyYou list
     * @param buyYou from BuyYou
     */
    public void addBuyList(BuyYou buyYou){
        buyList.add(buyYou);
    }

    /**
     * Get buyList
     * @return buyList
     */
    public List<BuyYou> getBuyList(){
        return buyList;
    }
}
