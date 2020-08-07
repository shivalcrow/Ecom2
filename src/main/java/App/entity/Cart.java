package App.entity;
public class Cart {
	private SanPham sanpham;
	private int quantity;
	
	public Cart() {
    }
	public Cart(SanPham sanpham, int quantity) {
        this.sanpham = sanpham;
        this.quantity = quantity;
    }
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
