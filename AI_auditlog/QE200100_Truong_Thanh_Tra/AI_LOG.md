# BÁO CÁO NHẬT KÝ SỬ DỤNG VÀ KIỂM DUYỆT TRÍ TUỆ NHÂN TẠO (AI AUDIT LOG)

## 1. Thông tin định danh phân hệ
* **Tên dự án:** Hệ thống Quản lý Bán hàng & Kho hàng (`com.mycompany.group5`)
* **Thành viên thực hiện:** Trà (Phụ trách phân hệ Bán hàng & Hóa đơn)
* **Phạm vi đóng góp:** 
  * **Tầng Thực thể (`model`):** `InvoiceLine.java`, `Invoice.java`
  * **Tầng Nghiệp vụ (`service`):** `SalesService.java`
* **Công cụ AI hỗ trợ:** Google Gemini (Large Language Model)
* **Vai trò của AI:** Trợ lý gợi ý mã nguồn khung (boilerplate code), hỗ trợ rà soát lỗi biên dịch (compile-time debugging) trên IDE NetBeans và đối chiếu tính nhất quán với biểu đồ lớp (UML Class Diagram).

---

## 2. Ma trận truy vết quá trình tương tác (Prompt & Iteration Traceability Matrix)

| Phiên | Mục tiêu kỹ thuật | Nội dung yêu cầu (Prompt) | Phản hồi ban đầu của AI | Đánh giá & Kiểm duyệt của Sinh viên | Giải pháp hiệu chỉnh cuối cùng |
| :---: | :--- | :--- | :--- | :--- | :--- |
| **01** | Xây dựng thực thể `InvoiceLine` và `Invoice` | Khởi tạo `InvoiceLine` và `Invoice` đồng bộ quy tắc kiểm tra dữ liệu với `Product`, `Cart`, `CartItem`. | Sinh mã nguồn với các ràng buộc: `unitPrice` ($1 - 10,000,000$), `quantity` ($1 - 1,000$), tối đa $20$ dòng hóa đơn. | Phát hiện AI sinh dư constructor phụ và khi đưa vào NetBeans gặp lỗi cú pháp Regex (`[A-20-9]+`), nhầm `IllegalAccessException` và dùng sai tham chiếu `this.` trước khi gán biến. | Chuẩn hóa về 1 constructor 4 tham số (`productId`, `productName`, `unitPrice`, `qty`), sửa biểu thức chính quy thành `[A-Z0-9]+` và kiểm tra trực tiếp trên tham số đầu vào. |
| **02** | Triển khai tầng nghiệp vụ `SalesService` | Xây dựng lớp `SalesService` xử lý bán hàng và gọi đa hình `validateSale` từ `Product`. | Đề xuất cấu trúc lưu trữ bằng `Map<String, Invoice>` và các hàm thao tác giỏ hàng trực tiếp. | Cấu trúc AI đề xuất tự do chưa khớp với bản thiết kế UML của nhóm; phát sinh xung đột số lượng tham số constructor (`invoiceCounter`) khi kết hợp với tính năng tự sinh code của NetBeans. | Bác bỏ cấu trúc `Map`, cung cấp bản vẽ UML chuẩn để ép AI tuân thủ đúng kiến trúc `List<Invoice>` và danh sách phương thức của nhóm. |
| **03** | Đồng bộ hóa toàn diện theo thiết kế UML | Cung cấp 3 sơ đồ lớp UML của `InvoiceLine`, `Invoice`, `SalesService` để tái cấu trúc. | Viết lại 3 lớp khớp hoàn toàn về tên thuộc tính, phạm vi truy cập (`private`/`public`) và kiểu trả về. | Kiểm tra chéo giữa `SalesService` và `Invoice`: phát hiện lệch hàm `getTotalAmount()` so với `getTotal()` trên UML. | Cập nhật `Invoice` với 3 thuộc tính tài chính (`subtotal`, `discount`, `total`) và hàm nội bộ `calculateAmounts()`, giải quyết triệt để lỗi biên dịch. |

---

## 3. Nhật ký phát hiện và xử lý lỗi chuyên sâu (Technical Defect & Debugging Log)

Trong quá trình tích hợp mã nguồn vào Apache NetBeans IDE 17, sinh viên đã trực tiếp phát hiện, phân tích nguyên nhân gốc rễ (Root Cause Analysis) và khắc phục 4 nhóm lỗi kỹ thuật quan trọng:

### 3.1. Lỗi biểu thức chính quy và Ngoại lệ kiểm tra (Regex & Checked Exception)
* **Hiện tượng:** Trình biên dịch báo lỗi tại dòng kiểm tra định dạng mã sản phẩm trong `InvoiceLine.java`.
* **Nguyên nhân gốc rễ:** Gõ nhầm ký tự `'Z'` thành số `'2'` trong dải ký tự `"[A-20-9]+"` (khiến mã ASCII của `'A'` lớn hơn `'2'`, gây lỗi cú pháp Regex) và gọi nhầm `IllegalAccessException` (một Checked Exception bắt buộc khai báo `throws`) thay vì Runtime Exception.
* **Khắc phục:** Chuẩn hóa lại biểu thức `"[A-Z0-9]+"` và sử dụng `IllegalArgumentException` đồng bộ với lớp `Product`.

### 3.2. Lỗi truy cập thuộc tính chưa khởi tạo (Uninitialized Field Reference)
* **Hiện tượng:** Các điều kiện kiểm tra đầu vào `if (this.productId == null)` hoặc `if (this.quantity < 1)` luôn ném ra ngoại lệ ngay cả khi truyền dữ liệu hợp lệ.
* **Nguyên nhân gốc rễ:** Sử dụng từ khóa `this.` trong phần đầu của constructor khiến Java kiểm tra giá trị mặc định của thuộc tính lớp (`null` hoặc `0`) trước khi tham số đầu vào được gán.
* **Khắc phục:** Loại bỏ từ khóa `this.` trong toàn bộ khối lệnh `if` đầu hàm khởi tạo để đánh giá đúng tham số truyền vào, đồng thời gán biến đã chuẩn hóa (`newId`, `newName`) vào thuộc tính đối tượng.

### 3.3. Lỗi chuỗi gọi hàm khởi tạo (Constructor Chaining Mismatch)
* **Hiện tượng:** Lời gọi `this(...)` trong `InvoiceLine` và `SalesService` bị báo đỏ với lỗi *cannot find symbol / constructor*.
* **Nguyên nhân gốc rễ:** Do công cụ Generate Constructor của IDE tự động đưa các biến dẫn xuất (`lineTotal`, `invoiceCounter`) vào danh sách tham số, làm lệch chữ ký hàm (method signature) giữa các constructor nạp chồng.
* **Khắc phục:** Loại bỏ các biến tự tính toán khỏi tham số đầu vào của constructor; thiết lập cơ chế tự tính `this.lineTotal = unitPrice * qty` trong `InvoiceLine` và khởi tạo `this.invoiceCounter = 0` bên trong `SalesService`.

### 3.4. Lỗi kiểm tra nhầm biến độ dài chuỗi (Variable Shadowing / Typo Bug)
* **Hiện tượng:** Kiểm tra độ dài tên sản phẩm nhưng lại gọi `if (newId.length() > 80)`.
* **Nguyên nhân gốc rễ:** Sao chép khối lệnh kiểm tra từ `newId` xuống `newName` nhưng chưa cập nhật tên biến.
* **Khắc phục:** Sửa thành `if (newName.length() > 80)` để đảm bảo ràng buộc tên sản phẩm không vượt quá $80$ ký tự.

---

## 4. Đánh giá chất lượng kiến trúc và Nghiệp vụ (Architectural & Business Logic Verification)

* **Tuân thủ nguyên lý Đóng gói và Bất biến (Encapsulation & Immutability):**
  * Lớp `InvoiceLine` được thiết kế bất biến hoàn toàn (chỉ cung cấp các phương thức `getter`, không có `setter`), đảm bảo dữ liệu lịch sử của từng dòng hóa đơn không thể bị chỉnh sửa sau khi phát hành.
  * Danh sách chi tiết hóa đơn (`getLines()`) và lịch sử hóa đơn (`getAllInvoices()`) đều được bọc qua `Collections.unmodifiableList()`, ngăn chặn việc can thiệp trực tiếp vào cấu trúc dữ liệu nội bộ từ bên ngoài lớp (Defensive Copying).
* **Áp dụng Đa hình trong kiểm tra nghiệp vụ (Polymorphic Validation):**
  * Trong `SalesService.preview(Cart)` và `SalesService.checkout(Cart)`, lời gọi `product.validateSale(saleDate, quantity)` được kích hoạt dựa trên kiểu thực tế của đối tượng lúc chạy (Runtime Polymorphism): tự động kiểm tra tồn kho đối với `RegularProduct` và kiểm tra bổ sung điều kiện quá hạn (`saleDate.isAfter(expiryDate)`) đối với `ExpiringProduct`.
* **Đảm bảo tính nguyên tố khi thanh toán (Two-Phase Checkout Atomicity):**
  * Quy trình `checkout(Cart)` được chia làm 2 pha tách biệt: **Pha 1** duyệt kiểm tra `validateSale` toàn bộ sản phẩm trong giỏ; **Pha 2** mới thực hiện trừ tồn kho (`deductInventory`) và sinh `InvoiceLine`. Cơ chế này đảm bảo nếu bất kỳ mặt hàng nào ở cuối giỏ bị lỗi (hết hạn hoặc thiếu tồn kho), giao dịch sẽ dừng lại ngay ở Pha 1 mà không làm sai lệch số lượng tồn kho của các mặt hàng đứng trước.

---

## 5. Tuyên bố Liêm chính Học thuật (Academic Integrity Statement)

Sinh viên cam kết công cụ AI chỉ đóng vai trò hỗ trợ tăng tốc độ viết mã khung và giải thích thông báo lỗi của trình biên dịch. Toàn bộ kiến trúc phân tầng (`model`, `service`, `storage`), biểu đồ thiết kế lớp UML, các ngưỡng ràng buộc nghiệp vụ và việc kiểm thử tích hợp trên môi trường NetBeans đều do sinh viên và nhóm trực tiếp thiết kế, kiểm soát và chịu trách nhiệm hoàn toàn về mặt học thuật.