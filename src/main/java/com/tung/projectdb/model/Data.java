package com.tung.projectdb.model;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@lombok.Data
public class Data {
    @Getter
    public static List<Item> items = new LinkedList<>();

    @Setter
    @Getter
    public static List<TaiKhoan> taiKhoans = new LinkedList<>();

    @Getter
    public static String logs = "";

    public static void writeLogs(String is, String log) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        logs = formatter.format(date) + " - " + is + " - " + log + "<br>" + logs;
    }

    public static TaiKhoan getTaiKhoanByKey(String key) {
        for (TaiKhoan taiKhoan : taiKhoans) {
            if(taiKhoan.getKey().equals(key))
                return taiKhoan;
        }
        return null;
    }

    public static Item getItemByKey(int ma){
        for(Item item:items){
            if(item.getMaSanPham()==ma)
                return item;
        }
        return null;
    }
    public static void create() {
        items.add(new Item("image/1.jpg", "Nike Air Max Torch 4", "Bạn sẽ bị xé toạc đường đua và thiết lập kỷ " +
                "lục cao ngất trời với Đôi giày chạy Bộ Nike Air Max Torch 4 mới của mình. Từ thời điểm bạn trượt chúng lên và " +
                "ren chúng lên, bạn sẽ được bao bọc trong phong cách thoải mái và kiểu dáng đẹp mà bạn sẽ chạy trở lại hàng ngày" +
                ". Cấu trúc nhẹ và thoáng khí của Air Max Tourch cho phép bạn tăng tốc độ mà không bị sa lầy với các chân ướt " +
                "đẫm mồ hôi hoặc khó chịu với đế giữa Air Max nhẹ và hỗ trợ và đế ngoài kéo cao su bền, nhưng linh hoạt đảm bảo " +
                "rằng mọi bước được thực hiện với sự tự tin và thoải mái.", 0, 85, 4));
        items.add(new Item("image/2.jpg", "Nike Air Max Excee", "Kỷ niệm một yêu thích throwback cổ điển với m" +
                "ột ống kính mới với Nike Air Max Excee. Lấy cảm hứng từ Nike Air Max 90, những chiếc sneak này là bất cứ thứ " +
                "ì nhưng yếu - được chế tạo với phần trên bền, nhưng thoáng khí, đế giữa Air có thể nhìn thấy hỗ trợ và phong c" +
                "ách và đế đệm siêu mềm, sẽ là một thách thức để không yêu chúng ngay khi bạn ren chúng lên! Nhưng chờ đã - còn" +
                " nhiều hơn nữa! Air Max Excee thể thao một số tính linh hoạt nghiêm trọng trong thiết kế của nó, có nghĩa là bạ" +
                "n có thể dễ dàng nhảy từ tòa án sang chơi với những đứa trẻ trong quần short của cha bạn trong thời gian kỷ lụ" +
                "c.", 1, 90, 25));
        items.add(new Item("image/3.jpg", "Nike Revolution 5", "Nike Revolution 5 đệm sải chân của bạn với bọt" +
                " mềm để giữ cho bạn chạy thoải mái. Vật liệu đan nhẹ quấn chân của bạn trong hỗ trợ thoáng khí, trong khi một " +
                "thiết kế tối giản phù hợp ở bất cứ nơi nào trong ngày của bạn đưa bạn.", 2, 65, 15));
        items.add(new Item("image/4.jpg", "Under Armour Assert 9", "mô tả", 3, 64, 7));
        items.add(new Item("image/5.jpg", "New Balance ML515", "Phong cách lấy cảm hứng từ retro và màu sắc t" +
                "ương phản kết hợp để cung cấp cho bạn một cổ điển vượt thời gian trong New Balance ML515 Những sneaks này có " +
                "thể trông retro nhưng chúng đi kèm với tất cả các tiện nghi hiện đại: đế đệm bọt EVA, đơn vị gót chân TPU để " +
                "hỗ trợ thêm, và lớp lót vải siêu mềm và thoải mái. Thể hiện phong cách trường học cũ của bạn với New Balance M" +
                "L515!", 4, 70, 31));
        items.add(new Item("image/6.jpg", "Nike Tanjun", "Đối với phong cách thể thao tươi mới, ren lên Nike Ta" +
                "njun! Những đôi giày thể thao này có phần trên lưới hoa văn kim cương, đế ngoài EVA với bánh quế cho lực kéo n" +
                "hẹ và logo ® Swoosh tương phản. Từ denim đến mồ hôi, Nike Tanjun sẽ giữ cho bạn di chuyển trong phong " +
                "cách!", 5, 65, 3));

        taiKhoans.add(new TaiKhoan("1904", "1904"));
    }
}
