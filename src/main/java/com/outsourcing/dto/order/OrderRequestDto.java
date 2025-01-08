package com.outsourcing.dto.order;

public class OrderRequestDto {

    private String memberName;  // 회원명
    private String storeName;   // 가게명
    private int amount;         // 금액
    private String menuName;    // 메뉴명
    private String status;      // 상태

    public OrderRequestDto(String memberName, String storeName, int amount, String menuName, String status) {
        this.memberName = memberName;
        this.storeName = storeName;
        this.amount = amount;
        this.menuName = menuName;
        this.status = status;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAmount() {
        return amount;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getStatus() {
        return status;
    }
}