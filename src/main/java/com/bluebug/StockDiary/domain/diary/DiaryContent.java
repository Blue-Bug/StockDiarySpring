package com.bluebug.StockDiary.domain.diary;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
@Getter
@Setter
public class DiaryContent {
    //종목명
    @NotEmpty
    private String item;
    //증권사
    @NotEmpty
    private String bank;
    //매매 구분
    @NotEmpty
    private String trade_category;
    //체결 단가
    @NotEmpty
    private Long transaction_price;
    //체결 수량
    @NotEmpty
    private Long transaction_volume;
    //매수 일자
    private Date buy_date;
    //매도 일자
    private Date sell_date;
    //보유 기간
    private Date holding_period;
    //손익
    private Long profit_loss;
    //수익률
    private float profit_rate;
    //매매 비용
    private Long trading_cost;
    //총 체결 금액
    private Long total_price;
    //매매 이유
    private String trading_reason;

    public DiaryContent(){
    }
    public DiaryContent(String item, String bank, String trade_category, Long transaction_price, Long transaction_volume) {
        this.item = item;
        this.bank = bank;
        this.trade_category = trade_category;
        this.transaction_price = transaction_price;
        this.transaction_volume = transaction_volume;
    }
}
