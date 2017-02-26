package data.domain;

import java.sql.Date;

/**
 * Created by a on 2017/2/23.
 *   author:wuguoping
 */
public class sellers {
    private String sellerId;
    private String sellerTitle;
    private String sellerAccount;
    private String sellerPwd;
    private String sellerDscp;
    private int sellerLevel;
    private Date sellerRegDate;
    private String sellerPhoneNum;
    private int sellerStatus;

    public sellers(){

    }
    public sellers(String sellerId, String sellerTitle, String sellerAccount, String sellerPwd,
                   String sellerDscp, int sellerLevel, Date sellerRegDate, String sellerPhoneNum, int sellerStatus) {
        this.sellerId = sellerId;
        this.sellerTitle = sellerTitle;
        this.sellerAccount = sellerAccount;
        this.sellerPwd = sellerPwd;
        this.sellerDscp = sellerDscp;
        this.sellerLevel = sellerLevel;
        this.sellerRegDate = sellerRegDate;
        this.sellerPhoneNum = sellerPhoneNum;
        this.sellerStatus = sellerStatus;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerTitle() {
        return sellerTitle;
    }

    public void setSellerTitle(String sellerTitle) {
        this.sellerTitle = sellerTitle;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getSellerPwd() {
        return sellerPwd;
    }

    public void setSellerPwd(String sellerPwd) {
        this.sellerPwd = sellerPwd;
    }

    public String getSellerDscp() {
        return sellerDscp;
    }

    public void setSellerDscp(String sellerDscp) {
        this.sellerDscp = sellerDscp;
    }

    public int getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(int sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public Date getSellerRegDate() {
        return sellerRegDate;
    }

    public void setSellerRegDate(Date sellerRegDate) {
        this.sellerRegDate = sellerRegDate;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
    }

    public int getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(int sellerStatus) {
        this.sellerStatus = sellerStatus;
    }
}
