use shoesshop;
alter table category auto_increment 1;
alter table brand auto_increment 1;
alter table product auto_increment 1;
insert into category (name) values ('Giày tây'),('Giày thể thao');
insert into brand (brand_name) values ('Adidas'),('Oxford'),('Nike');
insert into size (size) values (38),(39),(40),(41),(42),(43);

insert into product (color, describle, image, price, product_name, quantity, brand_id, category_id, status) values
                    ('Đen xanh',
                    'Giày Nike Air Jordan 1 Low là một trong những dòng giày sneaker nổi tiếng nhất của thương hiệu Jordan, một mẫu giày mà mỗi khi xuất hiện luôn tạo ra cơn sốt trên toàn cầu, một mẫu giày huyện thoại của biết bao nhiêu thế hệ.
                    Phần Upper với chất liệu da cao cấp phối màu đẳng cấp, Giày Nike Air Jordan 1 Low là một siêu phẩm đỉnh cao của các tín đồ sneaker.',
                    'cua-hang-giay-Nike-Air-Jordan-1-Low-UNC-AO9944-441-tai-hcm.jpg',
                    70,'GIÀY NIKE AIR JORDAN 1 LOW-G',50,3,2,0),
                    ('Trắng đen',
                    'Giày Nike Ebernon Low Premium là mẫu giày thời trang có thiết kế đẹp của Nike, chất liệu da cao cấp và đế cao su bền chắc sẽ khiến bạn vô cùng hài lòng khi sử dụng.',
                    'Giay-Nike-Air-Focer-1-NY-Xanh-800x800.jpg',
                    70,'GIÀY NIKE EBERNON LOW PREMIUM',40,3,2,0),

                   ('Xanh Trắng',
                    'Giày Nike Air Jordan 1 Low là một trong những dòng giày sneaker nổi tiếng nhất của thương hiệu Jordan, một mẫu giày mà mỗi khi xuất hiện luôn tạo ra cơn sốt trên toàn cầu, một mẫu giày huyện thoại của biết bao nhiêu thế hệ.
                    Phần Upper với chất liệu da cao cấp phối màu đẳng cấp, Giày Nike Air Jordan 1 Low là một siêu phẩm đỉnh cao của các tín đồ sneaker.',
                    'giay-nike-air-force-1-trang-logo-soc-tho-cam-rep-11_-2.jpg',
                    70,'GIÀY NIKE AIR JORDAN 1 LOW',50,3,2,0),

                    ('Trắng đen',
                    'Giày Nike Ebernon Low Premium là mẫu giày thời trang có thiết kế đẹp của Nike, chất liệu da cao cấp và đế cao su bền chắc sẽ khiến bạn vô cùng hài lòng khi sử dụng.',
                    'Giày-Nike-Air-Force-1-siêu-cấp.jpg',
                    500,'Nike Air Jordan 1 High OG',40,3,2,0),

                    ('Đỏ trắng',
                    'Giày Nike Air Jordan 1 Low là một trong những dòng giày sneaker nổi tiếng nhất của thương hiệu Jordan, một mẫu giày mà mỗi khi xuất hiện luôn tạo ra cơn sốt trên toàn cầu, một mẫu giày huyện thoại của biết bao nhiêu thế hệ.
                    Phần Upper với chất liệu da cao cấp phối màu đẳng cấp, Giày Nike Air Jordan 1 Low là một siêu phẩm đỉnh cao của các tín đồ sneaker.',
                    'images.jpg',
                    180,'Nike Air Jordan 1 Low Bred Toe',50,3,2,0),

                    ('Trắng đen',
                    'Giày Nike Ebernon Low Premium là mẫu giày thời trang có thiết kế đẹp của Nike, chất liệu da cao cấp và đế cao su bền chắc sẽ khiến bạn vô cùng hài lòng khi sử dụng.',
                    'images (1).jpg',
                    500,'Nike Air Jordan 1 High OG-1',40,3,2,0),

                    ('Đen','Chất liệu: Da bò thật toàn bộ từ châu Âu<br>
                    Đường may chi tiết, tỉ mỉ theo tiêu chuẩn.<br>
                    Đế giày chắc chắn, chống trơn trượt.<br>
                    Màu sắc trang nhã, hài hòa.<br>
                    Thiết kế hiện đại, sang trọng phù hợp với các quý ông lịch lãm.<br>
                    Sản phẩm giày tây nam thích hợp phối cùng quần âu, kaki, trang phục lịch sự. <br>
                    Màu: Đen<br>
                    Kích thước: 38 – 43',
                    'desktop-wallpaper-pair-of-black-dress-shoes-·-stock-formal-shoes.jpg',
                    70,'Giày da nam kiểu dáng Oxford GNLAAZ01-1-D 2',100,2,1,0),

                    ('Nâu',
                    'Thông tin chi tiết
                     Từng đường may kép tỉ mỉ, chắc chắn chạy quanh giày
                     Thiết kế thắt dây
                     Họa tiết đục lỗ tạo hoa văn quanh miệng và mũi giày
                     Kiểu dáng giày tây nam mũi tròn
                     Đế giày thiết kế chống trơn, trượt
                     Màu: Nâu',
                    'pngtree-dr-iii-s-black-oxford-shoes-on-an-old-wooden-floor-image_2542396.jpg',
                    60,'Giày da Oxford Brogue mũi tròn GNLA3368-N',100,2,1,0),

                    ('Nâu',
                    'Thông tin chi tiết
                     Kiểu dáng: Giày Oxford Brogues
                     Thiết kế: Hiện đại, thời trang
                     Chất liệu: Da bò cao cấp thật 100%
                     Màu sắc: Nâu
                    Sản xuất tại Đồ da LaForce ',
                    'pngtree-pair-of-brown-leather-oxford-shoes-picture-image_2658622.jpg',
                    66,'Giày tây nam Oxford Brogue GNLA135-1-CF',100,2,1,0);

insert into product_size(product_id, size_id) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),
                                                     (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),
                                                     (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),
                                                     (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),
                                                     (5,1),(5,2),(5,3),(5,4),(5,5),(5,6),
                                                     (6,1),(6,2),(6,3),(6,4),(6,5),(6,6),
                                                     (7,1),(7,2),(7,3),(7,4),(7,5),(7,6),
                                                     (8,1),(8,2),(8,3),(8,4),(8,5),(8,6),
                                                     (9,1),(9,2),(9,3),(9,4),(9,5),(9,6);

insert into role (role_name) values ('ROLE_ADMIN'),('ROLE_USER');
insert into country (country_name) values ('Việt Nam'),('China'),('Singapore');



DELIMITER $$
CREATE TRIGGER check_product_quantity
    AFTER INSERT ON oderdetail FOR EACH ROW
BEGIN
    DECLARE product_quantity INT;
#     DECLARE latest_oder_id INT;
    SELECT quantity INTO product_quantity
    FROM product
    WHERE id = NEW.product_id;
    IF product_quantity >= NEW.quantity THEN
        UPDATE product
        SET quantity = quantity - NEW.quantity
        WHERE id = NEW.product_id;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không đủ sản phẩm trong kho';


    END IF;
END;
$$
DELIMITER ;

# DROP TRIGGER check_product_quantity;
