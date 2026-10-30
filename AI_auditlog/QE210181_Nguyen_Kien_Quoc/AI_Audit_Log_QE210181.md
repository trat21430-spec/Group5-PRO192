🤖 NHẬT KÝ SỬ DỤNG AI (AI AUDIT LOG)
Thông tin	Chi tiết
Họ và tên	Nguyễn Kiến Quốc
MSSV	QE210181
Vai trò	Thành viên (Sản phẩm & Đa hình) – Nhóm 5 (SE21B – PRO192 FA26)
Đề tài	P02 – Mini Mart
Phần được giao	Product, RegularProduct, ExpiringProduct, SaleValidationException
📋 Danh sách các lần sử dụng AI hỗ trợ
Lần 1: Giải thích yêu cầu các đề tài và chọn đề P02
Ngày thực hiện: 16/10/2026
Công cụ AI sử dụng: ChatGPT
Mục đích sử dụng:
Đưa đề bài của các đề tài P01–P05 cho AI để giải thích yêu cầu và so sánh độ khó đối với người mới học Java/OOP.
AI xếp hạng độ dễ: P02 > P04 > P05 > P01 > P03 và đề xuất chọn P02 – Mini Mart vì:
Luồng nghiệp vụ dễ hình dung (Product → Cart → Invoice → lưu file).
Ít quan hệ dữ liệu chồng chéo.
Phần đa hình dễ trình bày (product.validateSale(...) được gọi thông qua Product).
Có sẵn bộ dữ liệu 5 sản phẩm và các ca kiểm thử MM01–MM11.
Phần sinh viên tự kiểm tra và điều chỉnh:
Sau khi nhận câu trả lời của AI, em tự đọc lại đề gốc của cả 5 đề tài để đối chiếu, không chọn đề chỉ dựa trên lời khuyên của AI.
Việc phân đề cuối cùng được thực hiện bằng cách bốc thăm ngẫu nhiên; kết quả nhóm được P02, trùng với đề tài mà AI đã gợi ý.
Lần 2: Giải thích Class Diagram và kiểm tra lỗi
Ngày thực hiện: 26/10/2026
Công cụ AI sử dụng: Claude
Mục đích sử dụng:
Đưa ảnh sơ đồ lớp và file đề P02 (.docx) cho AI để giải thích đề bài: luồng tìm hàng → lập giỏ → kiểm tra khả năng bán → tính giảm giá → xác nhận hóa đơn → trừ kho → lưu file. AI cũng nêu các quy tắc dễ sai:
Tiền dùng kiểu long.
Mã sản phẩm chuẩn hóa bằng toUpperCase().
Giới hạn số lượng và tồn kho.
Hàng có hạn được bán khi ngày bán ≤ hạn sử dụng.
Giảm 5% khi tổng tiền ≥ 200.000, làm tròn 0,5 lên.
Hóa đơn lưu bản chụp (snapshot) giá và tên sản phẩm.
Xác nhận hóa đơn theo kiểu "tất cả hoặc không gì cả".
Nhờ AI giải thích vai trò từng lớp trong sơ đồ: Product / RegularProduct / ExpiringProduct, ProductService, Cart / CartItem, SaleService, Invoice / InvoiceLine, FileStorageManager.
Nhờ AI kiểm tra lỗi của sơ đồ. AI chỉ ra các lỗi chính:
SaleService kế thừa Cart là sai (nên là quan hệ dependency).
Tiền dùng double thay vì long.
Product chưa được khai báo abstract.
validateSale không báo được lý do lỗi.
setInventory để công khai.
Hai hàm lưu file riêng biệt dễ làm lệch dữ liệu.
Kiểu trả về của loadProducts / saveProducts không đồng nhất.
Thiếu cơ chế khôi phục bộ đếm mã hóa đơn.
Một số lỗi nhỏ về tên tham số và quan hệ giữa các lớp.
AI đưa ra hướng viết code mẫu cho Product, hai lớp con, giỏ hàng, preview, checkout và lưu/đọc file.
Phần sinh viên tự kiểm tra và điều chỉnh:
Sơ đồ lớp bản cuối do leader (Kiên) chỉnh sửa và chốt; cả nhóm viết code theo bản này, không dùng nguyên bản AI đề xuất.
Đọc hiểu phần giải thích đề bài và tự đối chiếu với file đề gốc để chắc chắn AI không hiểu sai yêu cầu.
Tự kiểm tra từng lỗi mà AI nêu, đánh giá lỗi nào thực sự đúng, và xác nhận việc sửa các lỗi theo yêu cầu của thầy Khoa.
Chỉ dùng phần code mẫu của AI để tham khảo hướng làm, không sao chép trực tiếp vào bài nộp.
Lần 3: Giải thích nhiệm vụ và viết code phần được giao
Ngày thực hiện: 29/10/2026
Công cụ AI sử dụng: Claude (Anthropic)
Mục đích sử dụng:
Giải thích tin nhắn phân công của leader và các thuật ngữ trong đó (leader, module, class diagram, nghiệp vụ, ...).
Xác định các file thuộc phần của mình theo cây thư mục: Product, RegularProduct, ExpiringProduct (thư mục model/) và SaleValidationException (thư mục exception/).
Ban đầu nhờ AI viết bản code hoàn chỉnh của cả 4 file theo sơ đồ lớp. (Trong ngày, em điều chỉnh cách dùng AI: từ nhờ viết code hoàn chỉnh sang nhờ giải thích code và đưa ra hướng code, để em hiểu và tự làm chủ phần của mình.) Nội dung code AI đưa ra gồm:
Product: lớp abstract; thuộc tính id, name, price (long), inventory; getter/setter; addInventory, deductInventory (trả về boolean); validateSale là phương thức abstract.
RegularProduct và ExpiringProduct: ghi đè validateSale (hàng có hạn thêm thuộc tính expiryDate và kiểm tra ngày bán).
SaleValidationException: kế thừa RuntimeException để chữ ký validateSale(...) : void trong sơ đồ không phải khai báo throws.
AI nêu rõ các giả định thiết kế:
Product là lớp abstract.
Ngày bán sau ngày hết hạn thì không được bán.
Số lượng phải > 0 và không vượt quá tồn kho.
Phần sinh viên tự kiểm tra và điều chỉnh:
Đọc hiểu từng dòng code AI đưa ra: vì sao Product là abstract, vì sao validateSale được ghi đè ở lớp con (tính đa hình), vì sao SaleValidationException kế thừa RuntimeException.
Tự gõ lại / viết lại code trong môi trường của mình, chạy thử và biên dịch để chắc chắn không có lỗi.
Sau đó sửa code theo yêu cầu của leader (Kiên) để khớp với sơ đồ lớp mà nhóm đã chốt (tên thuộc tính, tên phương thức, kiểu dữ liệu, cấu trúc package).
Đối chiếu các giả định thiết kế của AI với đề gốc và sơ đồ lớp của nhóm; chỗ nào khác thì ưu tiên theo đề và theo quyết định của leader.
Lần 4: Hướng dẫn pull/clone và upload code lên GitHub
Ngày thực hiện: 30/10/2026
Công cụ AI sử dụng: Claude (Anthropic)
Mục đích sử dụng:
Giải thích sự khác nhau giữa pull và push, và các khái niệm repository, commit, branch, merge, conflict.
Hướng dẫn tạo nhánh riêng theo quy ước feature/<tên task>-<MSSV> (không push thẳng vào main).
Hướng dẫn upload 3 file vào model/ và 1 file vào exception/ ngay trên giao diện web GitHub, commit vào nhánh riêng, rồi báo leader kiểm tra và merge.
Hướng dẫn cách clone/pull repo về máy bằng Git (phương án thay thế khi làm việc trên máy cá nhân).
Phần sinh viên tự kiểm tra và điều chỉnh:
Em đã tự thực hiện từng bước theo hướng dẫn: tạo nhánh riêng theo đúng quy ước đặt tên, upload 4 file vào đúng thư mục (model/, exception/) và commit vào nhánh của mình.
Kiểm tra lại trên GitHub rằng các file nằm đúng vị trí và không đụng vào nhánh main, sau đó báo leader để kiểm tra và merge.