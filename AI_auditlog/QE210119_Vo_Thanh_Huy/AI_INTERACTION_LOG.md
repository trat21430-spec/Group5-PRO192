# AI Audit Log – PRO192 RBL

| Mục | Nội dung |
|---|---|
| Môn học | PRO192 |
| Task | CartItem.java, Cart.java (package `com.mycompany.group5.model`) |
| Ngày | 28/10/2026 |
| Công cụ AI | Claude (Anthropic) |
| Người thực hiện | Võ Thanh Huy - QE210119 |

## 1. Prompt đã dùng
> Giao task code CartItem và Cart; đặt ràng buộc riêng cho task; code dựa hoàn toàn vào class diagram; lưu AI audit log.
> Đính kèm: UML class diagram, cấu trúc thư mục dự án, bảng phân công 5 thành viên.

## 2. Phạm vi AI hỗ trợ
- Sinh code ban đầu cho `CartItem` và `Cart` theo đúng tên field, kiểu dữ liệu, signature trong diagram.
- Đề xuất các ràng buộc nghiệp vụ riêng cho 2 class này.

## 3. Ràng buộc đã áp dụng
**CartItem**
- Constructor chỉ gán giá trị; việc kiểm tra `productId` và `quantity` do `Cart` thực hiện trước khi tạo `CartItem`.
- `setQuantity`, `addQuantity` chỉ cập nhật khi giá trị hợp lệ (`> 0`), ngược lại bỏ qua. Không ném exception.
- Chỉ dùng kiểu chuẩn Java (`String`, `int`), không phụ thuộc `Product`.

**Cart**
- Constructor không ném exception: `saleDate` null → dùng ngày hiện tại; `items` null → tạo map rỗng; copy map để tránh sửa từ bên ngoài.
- `addItem`: từ chối nếu giỏ đã confirmed, id rỗng, quantity <= 0; sản phẩm đã có thì cộng dồn.
- `updateQuantity`: chỉ cập nhật sản phẩm đã có, `newQuantity > 0`.
- `removeItem`: trả `true` nếu thực sự xóa được.
- Giỏ đã `confirmed` thì không thêm/sửa/xóa.
- `getItems()` trả về map chỉ đọc (unmodifiable).
- `getItemCount()` = số dòng sản phẩm (`items.size()`).
- Không phụ thuộc `Product`/`ProductService` (kiểm tra tồn kho là việc của `SaleService`/Trà).

## 4. Phần người thực hiện tự kiểm tra / chỉnh sửa
- [ ] Đọc hiểu toàn bộ code, đối chiếu với diagram (tên field, method, kiểu trả về).
- [ ] Biên dịch và chạy thử.
- [ ] Viết test nhỏ (thêm trùng sản phẩm, update, remove, giỏ đã confirmed).
- [ ] Ghi lại các chỗ tự sửa so với bản AI: ...

## 5. Giả định cần xác nhận với nhóm
- `getItemCount()` đếm số loại sản phẩm, không phải tổng số lượng.
- `getItems()` trả về view chỉ đọc; Trà (SaleService) chỉ đọc giỏ, không sửa trực tiếp.

## 6. Cam kết
Tôi đã đọc, hiểu và chịu trách nhiệm về code nộp. AI chỉ là công cụ hỗ trợ.
## 7. Trạng thái audit

**Trạng thái hiện tại: ON-GOING — Chưa kết luận cuối cùng.**

Bản ghi ngày 28/10/2026 ghi nhận lần tương tác ban đầu với Claude AI để hỗ trợ sinh code cho `CartItem.java` và `Cart.java`.

Quá trình phát triển vẫn đang tiếp diễn. Code được AI sinh ra đang được xem xét, đối chiếu với UML và các yêu cầu của dự án. Tính chính xác và mức độ hoàn thiện của code chưa được xác nhận đầy đủ cho đến khi các bước kiểm tra tương ứng được thực hiện.

Các lần tương tác tiếp theo với Claude sẽ được ghi nhận bổ sung, bao gồm prompt mới, nội dung phản hồi, thay đổi đối với code, quyết định của người thực hiện và kết quả xác minh thực tế.

## 8. Lịch sử cập nhật

| Phiên bản | Thời điểm | Nội dung | Trạng thái |
|---|---|---|---|
| v0.1 | 28/10/2026 | Ghi nhận prompt ban đầu, phạm vi hỗ trợ và các ràng buộc cho `CartItem.java`, `Cart.java`. | Đã ghi nhận |
| v0.2 | [Ngày cập nhật] | Bổ sung các tương tác tiếp theo, thay đổi code và kết quả kiểm tra nếu có. | Đang thực hiện |
| v1.0 | [Ngày hoàn tất] | Tổng hợp kết quả cuối cùng sau khi hoàn thành việc rà soát và xác minh. | Chưa thực hiện |

*Lưu ý: Chỉ cập nhật phiên bản và trạng thái khi các hoạt động tương ứng thực sự diễn ra.*

## 9. Trách nhiệm của người thực hiện

Người thực hiện chịu trách nhiệm xem xét code do Claude AI đề xuất, đối chiếu với yêu cầu của dự án, thực hiện hoặc xác nhận các bước kiểm thử và quyết định nội dung được sử dụng trong sản phẩm cuối cùng.

Audit log này được cập nhật trong quá trình làm việc nhằm bảo đảm tính minh bạch về việc sử dụng AI. Những nội dung chưa được kiểm tra hoặc xác nhận sẽ tiếp tục được ghi nhận là đang chờ xử lý, thay vì được xem là đã hoàn thành.