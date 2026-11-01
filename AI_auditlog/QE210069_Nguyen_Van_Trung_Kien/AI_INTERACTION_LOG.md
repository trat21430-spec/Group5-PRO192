# 🤖 NHẬT KÝ SỬ DỤNG AI (AI AUDIT LOG)

- **Họ và tên:** Nguyễn Văn Trung Kiên
- **MSSV:** QE210069
- **Vai trò:** Trưởng nhóm (Leader) - Nhóm 5 (SE21B - PRO192 FA26)
- **Đề tài:** P02 – Mini Mart

---

## 📋 Danh sách các lần sử dụng AI hỗ trợ

### Lần 1: Rà soát Class Diagram, tư vấn cây thư mục và phân chia module

- **Ngày thực hiện:** 28/10/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Kiểm tra sơ đồ lớp (Class Diagram):** Đưa đề bài và hình ảnh sơ đồ lớp để AI kiểm tra tính hợp lý theo chuẩn OOP và yêu cầu đề bài. Nhờ AI phát hiện các lỗi lệch đề quan trọng (chuyển kiểu tiền tệ từ `double` sang `long`, sửa kiểu trả về của `validateSale` thành `void`, sửa typo trong `FileStorageManager`).
  2. **Gợi ý cây thư mục dự án:** Tư vấn cấu trúc package (`model`, `service`, `storage`, `exception`) và thư mục `data/` dùng đường dẫn tương đối.
  3. **Phân chia module độc lập cho 5 thành viên:** Lên phương án tách file cho 5 bạn trong nhóm để mỗi người làm một tập file riêng biệt, đảm bảo không bị xung đột (conflict) khi merge nhánh trên Git.
  4. **Tạo `.gitignore`:** Tư vấn và tạo file `.gitignore` để loại bỏ các file rác sinh ra trong quá trình build (`target/`, `.class`, cấu hình IDE NetBeans / IntelliJ / VS Code).

- **Phần sinh viên tự kiểm tra và điều chỉnh:**
  - Tự chỉnh sửa lại sơ đồ lớp trên Draw.io theo các điểm góp ý và hoàn thiện bản diagram chuẩn 10/10.
  - Yêu cầu chỉ tạo khung thư mục rỗng với file `.gitkeep`, không sinh code tự động để các thành viên tự lập trình theo đúng nhiệm vụ được giao.
  - Tự phân công nhiệm vụ cụ thể cho từng bạn trong nhóm dựa trên độ khó.

---

### Lần 2: Kiểm tra, test nghiệm thu code của thành viên nhóm (Võ Thanh Huy & Nguyễn Kiến Quốc)

- **Ngày thực hiện:** 30/10/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Kiểm tra và review code thành viên:** Sử dụng AI để rà soát, kiểm tra logic các đoạn code do **Võ Thanh Huy** và **Nguyễn Kiến Quốc** đã thực hiện và push lên Git (bao gồm các lớp `Cart.java`, `CartItem.java` và các lớp `Product`).
  2. **Thiết kế bộ test tự động (Unit Test):** Nhờ AI thiết kế và tạo test runner tự động `CartTestRunner.java` để kiểm thử toàn diện các quy tắc nghiệp vụ khắt khe của giỏ hàng (chuẩn hóa mã hoa, giới hạn 1–1000/dòng, gộp số lượng khi trùng mã, không vượt 1000, tối đa 20 mã/giỏ, đóng băng khi đã xác nhận `confirmed`, tính bất biến của unmodifiable map).
  3. **Thực thi kiểm thử thực tế:** Chạy trực tiếp 23 ca kiểm thử biên trên JDK 8, đối chiếu kết quả mong đợi với kết quả thực tế để nghiệm thu chất lượng code của Huy và Quốc.
- **Trạng thái (Status):** **COMPLETE** (Toàn bộ 23/23 ca kiểm thử đều ĐẠT 100%, nghiệm thu hoàn tất phần việc của Võ Thanh Huy và Nguyễn Kiến Quốc).
- **Phần sinh viên quan sát, phản biện và tự điều chỉnh (Human-in-the-loop):**
  - **Tình huống phản biện:** Trong quá trình rà soát code các lớp sản phẩm (`RegularProduct`, `ExpiringProduct`), AI có đưa ra ví dụ gợi ý gọi `super.validateSale(saleDate, quantity)` trong các lớp con để kế thừa phần kiểm tra của lớp cha.
  - **Phản biện của sinh viên:** Tôi đã phát hiện và phản biện lại ngay rằng phương thức `validateSale` được khai báo trong lớp cha `Product` là một **phương thức trừu tượng (`abstract`)**, tức là hoàn toàn không có phần thân (`body`). Trong Java, việc gọi `super` đến một phương thức `abstract` sẽ gây lỗi biên dịch (*"abstract method cannot be accessed directly"*). Do đó, tôi không làm theo gợi ý này của AI mà giữ nguyên việc cài đặt logic kiểm tra độc lập phù hợp với từng lớp con (`RegularProduct` kiểm tra tồn kho, `ExpiringProduct` kiểm tra cả hạn sử dụng).
- **Kế hoạch tiếp theo:** Tiếp tục phối hợp, rà soát và kiểm thử nghiệm thu các phần nghiệp vụ tiếp theo do **Lương Võ Gia Phúc** và **Trương Thanh Trà** thực hiện trước khi tích hợp vào hệ thống chung.

---

### Lần 3: Hỗ trợ rà soát, phản biện và Review Code cho thành viên (Trương Thanh Trà & Lương Võ Gia Phúc)

- **Ngày thực hiện:** 31/10/2026 – 01/11/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Rà soát chất lượng code của Trương Thanh Trà (`InvoiceLine.java`, `Invoice.java`, `SaleService.java`):**
     - Đưa mã nguồn của Trà cho AI phân tích để phát hiện các lỗi cú pháp tiềm ẩn, lỗi logic nghiệp vụ so với đề bài P02 và độ lệch so với Sơ đồ lớp (Class Diagram).
     - Rà soát tính bất biến (Immutability) của dòng hóa đơn, chính sách làm tròn và chiết khấu $5\%$, cũng như cơ chế thanh toán nguyên tử (All-or-Nothing).

- **Trạng thái (Status):** **COMPLETE** (Hoàn thành review, phát hiện và khắc phục toàn bộ 5 lỗi nghiêm trọng; toàn bộ mã nguồn của Trà và Phúc đạt chuẩn 10/10).

- **Phần sinh viên quan sát, phản biện và tự điều chỉnh (Human-in-the-loop):**
  - **Phát hiện và chỉ đạo sửa lỗi logic chí mạng trong `InvoiceLine.java`:** 
    - *Vấn đề phát hiện:* Trà viết kiểm tra `if (this.productId == null || ...)` ngay đầu constructor khi các thuộc tính đối tượng chưa được gán giá trị, khiến `this.productId` luôn bằng `null` và hàm khởi tạo luôn văng `IllegalArgumentException` 100% khi chạy.
    - *Hành động của Leader:* Nhận diện ngay lỗi nhầm lẫn giữa biến instance và tham số truyền vào; yêu cầu Trà bỏ tiền tố `this.` trong các câu lệnh `if` và kiểm tra trực tiếp tham số (`productId`, `productName`, `unitPrice`, `quantity`).
  - **Kiên quyết loại bỏ code thừa, bảo vệ tính chất Snapshot và chuẩn Class Diagram:**
    - Trà tự ý thêm các hàm `setter` (`setUnitPrice`, `setQuantity`...) và một constructor quái dị dùng toán tử 3 ngôi lồng ghép `InvoiceLine(Product product, int quantity)`.
    - *Phản biện của Leader:* Tôi phát hiện đoạn code toán tử 3 ngôi này vừa rối rắm, khó hiểu ("over-engineering"), vừa hoàn toàn không có trong Class Diagram. Quan trọng hơn, dòng hóa đơn là bản chụp lúc bán (Snapshot) mang tính bất biến (Immutable), tuyệt đối không được có `setter` để tránh việc sửa giá làm sai lệch hóa đơn cũ. Tôi đã yêu cầu Trà xóa bỏ toàn bộ các setter và constructor này, chỉ giữ đúng 1 constructor 4 tham số chuẩn theo diagram.
  - **Cứu đồ án khỏi việc trượt các ca kiểm thử nghiệm thu trong `Invoice.java`:**
    - *Vấn đề phát hiện:* Trong hàm `calculateAmounts()`, Trà cài đặt nhầm chính sách giảm giá của đề tài khác (giảm 10% khi $\ge 1.000.000$đ, 5% khi $\ge 500.000$đ). Nếu để nguyên, dự án sẽ trượt toàn bộ các ca test MM02, MM03, MM04 của giảng viên.
    - *Điều chỉnh của Leader:* Buộc Trà sửa lại đúng quy định duy nhất của đề tài P02: chỉ giảm $5\%$ khi tổng tiền $S \ge 200.000$đ, áp dụng công thức làm tròn 0,5 lên: `D = (S * 5 + 50) / 100`.
    - **Nắm vững bản chất OOP:** Khi thắc mắc việc gọi `product.validateSale(...)` trong khi phương thức này là `abstract` ở lớp cha `Product`, tôi đã cùng AI phân tích sâu cơ chế **Liên kết động (Dynamic Binding)** tại thời điểm Runtime của máy ảo Java (JVM). Qua đó, tôi hiểu tường tận cách JVM tự động kích hoạt hành vi kiểm tra của `RegularProduct` hoặc `ExpiringProduct` mà không cần `if-else`, chuẩn bị sẵn luận điểm vững chắc để thuyết trình trước hội đồng bảo vệ.
- **Kế hoạch tiếp theo:** Hoàn thiện nốt module lưu trữ và khôi phục dữ liệu (`FileStorageManager.java`) và xây dựng Menu Console hoàn chỉnh (`Group5.java`) để ráp nối toàn bộ hệ thống.

---

### Lần 4: Lập trình tầng Lưu trữ (`FileStorageManager.java`) và thiết kế Menu Console

- **Ngày thực hiện:** 01/11/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Hỗ trợ tư vấn và hướng dẫn code tầng Lưu trữ (`FileStorageManager.java`):**
     - Nhờ Antigravity làm trợ lý hướng dẫn cách tư duy kiến trúc và kết nối tầng Storage với `ProductService` và `SalesService` theo đúng yêu cầu đề bài P02 (không để AI tự viết code vào project, sinh viên yêu cầu AI hướng dẫn từng bước để tự tay lập trình).
     - Hướng dẫn cấu trúc định dạng file văn bản `.txt` phân tách bằng dấu gạch đứng `|`, mã hóa UTF-8, sử dụng đường dẫn tương đối `data/products.txt` và `data/invoices.txt`.
     - Hỗ trợ giải thích và gỡ các lỗi biên dịch trên NetBeans: phân biệt rõ `List<InvoiceLine>` thay vì `List<Invoice>` cho tham số của `Invoice`, tách biệt code đọc sản phẩm và code đọc hóa đơn.
     - Hỗ trợ chạy kiểm thử tự động độc lập xác nhận khả năng phục hồi Đa hình (`RegularProduct` / `ExpiringProduct`) và khôi phục toàn vẹn dữ liệu hóa đơn kèm tính toán tài chính.
  2. **Tư vấn kiến trúc Menu điều khiển (`Menu.java`):**
     - Nhờ AI tư vấn khung sườn menu 6 chức năng nghiệp vụ bắt buộc và các hàm nhập liệu phòng thủ (Defensive Input Helper) chống crash ứng dụng khi người dùng nhập sai kiểu dữ liệu.

- **Trạng thái (Status):** **COMPLETE** (Lớp `FileStorageManager.java` đã hoàn thành 100%, biên dịch sạch và vượt qua các bài kiểm thử lưu/đọc dữ liệu; hoàn tất khung sườn Menu sẵn sàng tích hợp).

- **Phần sinh viên quan sát, phản biện và tự điều chỉnh (Human-in-the-loop):**
  - **Phản biện loại bỏ hàm thừa `ensureParentDirectories()`:**
    - *Tình huống:* AI đề xuất thêm hàm `ensureParentDirectories()` vào `FileStorageManager` để tự động kiểm tra và tạo thư mục cha `data/`.
    - *Phản biện của sinh viên:* Tôi đã phát hiện và phản biện lại ngay rằng thư mục `data/` đã được tôi chủ động tạo và đẩy lên Git từ commit khởi tạo ban đầu (`Khoi tao cay thu muc du an cho 5 module`). Vì thư mục `data/` đã chắc chắn tồn tại trên mọi máy tính khi clone dự án về, nên hàm này là hoàn toàn dư thừa. Tôi yêu cầu loại bỏ hoàn toàn hàm này để giữ cho cấu trúc lớp ngắn gọn, sạch sẽ và khớp $100\%$ với Class Diagram đã duyệt.
  - **Chủ động tự code và rà soát lỗi:**
    - Kiên quyết từ chối việc để AI sinh code tự động vào dự án; yêu cầu AI giải thích bản chất để tự tay gõ từng dòng mã nguồn trên NetBeans IDE 17.
    - Trong quá trình lập trình, đã tự rà soát và khắc phục các lỗi lệch chuẩn: sửa tên hàm thành số nhiều (`saveProducts`, `loadProducts`, `saveInvoices`, `loadInvoices`) khớp với Class Diagram, sửa lỗi gõ nhầm `"INVOCES|"` thành `"INV|"`, và chuẩn hóa các câu thông báo lỗi ngoại lệ.

- **Kế hoạch tiếp theo:** Hoàn thiện nốt phần ráp nối `Menu.java` vào hàm `main` của `Group5.java` để sẵn sàng demo toàn diện 11 ca nghiệm thu MM01–MM11 trước giảng viên.

---

*(Nhật ký sẽ tiếp tục được cập nhật khi có các lần tương tác AI tiếp theo)*
