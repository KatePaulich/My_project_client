package sample.buy;

import java.time.LocalDate;

/**
 * BuyYoy class for storing buy information
 */
public class BuyYou {
    /**
     * id of a buy
     */
    private int id;
    /**
     * iduser of a buy
     */
    private int iduser;
    /**
     * namebuy of a buy
     */
    private String namebuy;
    /**
     * pricebuy of a buy
     */
    private String pricebuy;
    /**
     * idcost of a buy
     */
    private int idcost;
    /**
     * datebuy of a buy
     */
    private LocalDate datebuy;

    /**
     * This a constructor to initialize buy object
     * @param id an initial buy id
     * @param iduser an initial buy iduser
     * @param namebuy an initial buy namebuy
     * @param pricebuy an initial buy pricebuy
     * @param idcost an initial buy idcost
     * @param datebuy an initial buy datebuy
     */
    public BuyYou(int id, int iduser, String namebuy, String pricebuy,
                  int idcost, LocalDate datebuy){
        this.id = id;
        this.iduser = iduser;
        this.namebuy = namebuy;
        this.pricebuy = pricebuy;
        this.idcost = idcost;
        this.datebuy = datebuy;
    }

    /**
     * Get user id
     * @return buy id
     */
    public int getId() { return id; }

    /**
     * To set an id of a buy
     * @param id a buy id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Get buy iduser
     * @return buy iduser
     */
    public int getIduser() { return iduser; }

    /**
     * Get buy namebuy
     * @return buy namebuy
     */
    public String getNamebuy() { return namebuy; }

    /**
     * To set a namebuy of a buy
     * @param namebuy a buy namebuy
     */
    public void setNamebuy(String namebuy) { this.namebuy = namebuy; }

    /**
     * Get buy pricebuy
     * @return buy pricebuy
     */
    public String getPricebuy() { return pricebuy; }

    /**
     * To set a pricebuy of a buy
     * @param pricebuy a buy pricebuy
     */
    public void setPricebuy(String pricebuy) { this.pricebuy = pricebuy; }

    /**
     * Get buy idcost
     * @return buy idcost
     */
    public int getIdcost() { return idcost; }

    /**
     * Get buy datebuy
     * @return buy datebuy
     */
    public LocalDate getDatebuy() { return datebuy; }

}
