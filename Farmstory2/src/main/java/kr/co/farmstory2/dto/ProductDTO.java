package kr.co.farmstory2.dto;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

public class ProductDTO {
	private int pNo; 
	private int type;
	private String pName;
	private int price;
	private int delivery;
	private int stock;
	private int sold;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String seller;
	private String etc;
	private String rdate;
	private String path;
	// order 추가 필드
	private String count;
	private int total;
	private int totalPrice;
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setTotal(String total) {
		this.total = Integer.parseInt(total);
	}
	public String getTotalWithComma() {
		
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(total);
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public String getTotalPriceWithComma() {
		
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(totalPrice);
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setTotalPrice(String total, String delivery) {
		this.totalPrice = Integer.parseInt(total) + Integer.parseInt(delivery);
	}
	public void setTotalPrice(int total, int delivery) {
		this.totalPrice = total+delivery;
	}
	
	
	/////////////////////////////
	public ProductDTO() {
		
	}
	public ProductDTO(String path) {
		this.path = path;
	}
	
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = Integer.parseInt(pNo);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = Integer.parseInt(type);
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getPrice() {
		return price;
	}
	// 가격을 세자리 ,를 붙인 포맷으로 출력
	public String getPriceWithComma() {
		
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(price);
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setStock(String stock) {
		this.stock = Integer.parseInt(stock);
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getThumb1() {
		return thumb1;
	}
	public void setThumb1(String thumb1) {
		this.thumb1 = thumb1;
	}
	public void setThumb1ForRename(String thumb1) {
		this.thumb1 = fileRename(thumb1);
	}
	public String getThumb2() {
		return thumb2;
	}
	public void setThumb2(String thumb2) {
		this.thumb2 = thumb2;
	}
	public void setThumb2ForRename(String thumb2) {
		this.thumb2 = fileRename(thumb2);
	}
	public String getThumb3() {
		return thumb3;
	}
	public void setThumb3(String thumb3) {
		this.thumb3 = thumb3;
	}
	public void setThumb3ForRename(String thumb3) {
		this.thumb3 = fileRename(thumb3);
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	
	public String fileRename(String thumb) {
		
		int i = thumb.lastIndexOf(".");
		// 확장자
		String ext = thumb.substring(i);
		// 중복되지 않는 랜덤 이름 생성
		String uuid = UUID.randomUUID().toString();
		// 랜덤 이름과 확장자 붙여서 저장할 때 사용할 이름 생성
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+thumb);
		File f2 = new File(path+"/"+sName);
		f1.renameTo(f2);
		
		return sName;
	}
	
}
