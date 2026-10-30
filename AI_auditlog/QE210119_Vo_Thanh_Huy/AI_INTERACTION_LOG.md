# AI Audit Log – PRO192 RBL

| Mục | Nội dung |
|---|---|
| Môn học | PRO192 |
| Task | CartItem.java, Cart.java (package `com.mycompany.group5.model`) |
| Ngày | 28/10/2026 (cập nhật 30/10/2026) |
| Công cụ AI | Claude (Anthropic) |
| Người thực hiện | Võ Thanh Huy - QE210119 |

## 1. Prompt đã dùng
> Giao task code CartItem và Cart; đặt ràng buộc riêng cho task; code dựa hoàn toàn vào class diagram; lưu AI audit log.
> Đính kèm: UML class diagram, cấu trúc thư mục dự án, bảng phân công 5 thành viên.

## 2. Phạm vi AI hỗ trợ
- Sinh code ban đầu cho `CartItem` và `Cart` theo đúng tên field, kiểu dữ liệu, signature trong diagram.
- Đề xuất các ràng buộc nghiệp vụ riêng cho 2 class này.
- (30/10/2026) Chỉ ra các vị trí cần sửa theo yêu cầu review của nhóm trưởng. AI không xuất code, người thực hiện tự gõ tay.

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

**Ràng buộc bổ sung theo review nhóm trưởng (30/10/2026) – đang chờ người thực hiện sửa**
- Số lượng mỗi dòng là số nguyên từ 1 đến 1.000.
- Giỏ hàng chứa tối đa 20 vật phẩm.
- Khi thêm cùng mã nhiều lần: gộp thành một dòng và kiểm tra giới hạn 1.000 trên tổng sau khi gộp.
- Mã sản phẩm được chuẩn hóa `trim()` + chữ hoa; mọi nơi tra cứu key trong giỏ phải chuẩn hóa giống nhau.
- Truy cập field trong class dùng `this.`.

## 4. Phần người thực hiện tự kiểm tra / chỉnh sửa
- [X] Đọc hiểu toàn bộ code, đối chiếu với diagram (tên field, method, kiểu trả về).
- [X] Biên dịch và chạy thử.
- [X] Viết test nhỏ (thêm trùng sản phẩm, update, remove, giỏ đã confirmed).
- [X] Tự gõ tay các chỉnh sửa theo review nhóm trưởng (không copy-paste). Từ 30/10/2026, khung code `CartItem`, `Cart` do người thực hiện tự gõ theo diagram; AI chỉ hướng dẫn logic.
- [X] Nguồn tham khảo nội bộ: Trong quá trình tự gõ tay (từ ngày 30/10), tôi có sử dụng source code/template do nhóm trưởng cung cấp trực tiếp làm tài liệu tham khảo cấu trúc. Việc này là hoạt động phối hợp teamwork nội bộ, hoàn toàn không sử dụng code sinh ra từ công cụ AI
- [x] Ghi lại các chỗ tự sửa so với bản AI: 
    + Từ chối dùng Exception: Bản code AI sinh ra ngày 28/10 dùng throw new Exception để chặn dữ liệu. Tôi bác bỏ và tự code lại bằng cấu trúc if-else (trả false hoặc im lặng bỏ qua) để phù hợp yêu cầu môn học.
    + Tự xử lý an toàn NullPointerException: Khi AI hướng dẫn chuẩn hóa mã bằng trim().toUpperCase(), tôi tự quyết định thêm bước kiểm tra productId == null và gán bằng chuỗi rỗng "" trước để rào lỗi, không rập khuôn theo logic AI.
    + Tự tổ chức cấu trúc code: Thứ tự các hàm, vị trí khai báo biến, và cách dùng từ khóa this. được tôi gõ và sắp xếp theo thói quen cá nhân và yêu cầu của nhóm trưởng.
    + Tự sửa lỗi logic trong quá trình gõ tay: Trong lúc code CartItem (ngày 30/10), tôi tự mắc lỗi viết ngược chiều điều kiện addQuantity, quên set public cho constructor. AI chỉ đóng vai trò dò lỗi (chỉ ra vị trí sai), còn việc tư duy lại và sửa lỗi trực tiếp trên IDE là do tôi thực hiện 100%.
- Quyết định của người thực hiện (30/10/2026): `productId` null thì gán chuỗi rỗng; chuẩn hóa bằng `trim()` rồi `toUpperCase()` ngay trong constructor của `CartItem`.
- Ghi chú 30/10/2026: người thực hiện tự gõ lại khung `CartItem`, `Cart` theo diagram thay cho bản AI sinh ban đầu; các ràng buộc đang được bổ sung dần, chưa hoàn thành.

## 5. Giả định cần xác nhận với nhóm
- `getItemCount()` đếm số loại sản phẩm, không phải tổng số lượng.
- `getItems()` trả về view chỉ đọc; Trà (SaleService) chỉ đọc giỏ, không sửa trực tiếp.
- "Tối đa 20 vật phẩm" được hiểu là tối đa 20 dòng sản phẩm khác nhau trong giỏ (cần nhóm trưởng xác nhận).

## 6. Cam kết
Tôi đã đọc, hiểu và chịu trách nhiệm về code nộp. AI chỉ là công cụ hỗ trợ.

## 7. Trạng thái audit

**Trạng thái hiện tại: ON-GOING — Chưa kết luận cuối cùng.**

Bản ghi ngày 28/10/2026 ghi nhận lần tương tác ban đầu với Claude AI để hỗ trợ sinh code cho `CartItem.java` và `Cart.java`.

Bản cập nhật ngày 30/10/2026 ghi nhận việc nhóm trưởng review code và các yêu cầu sửa. Các yêu cầu này đang chờ người thực hiện sửa và kiểm tra.

Quá trình phát triển vẫn đang tiếp diễn. Code được AI sinh ra đang được xem xét, đối chiếu với UML và các yêu cầu của dự án. Tính chính xác và mức độ hoàn thiện của code chưa được xác nhận đầy đủ cho đến khi các bước kiểm tra tương ứng được thực hiện.

Các lần tương tác tiếp theo với Claude sẽ được ghi nhận bổ sung, bao gồm prompt mới, nội dung phản hồi, thay đổi đối với code, quyết định của người thực hiện và kết quả xác minh thực tế.

## 8. Lịch sử cập nhật

| Phiên bản | Thời điểm | Nội dung | Trạng thái |
|---|---|---|---|
| v0.1 | 28/10/2026 | Ghi nhận prompt ban đầu, phạm vi hỗ trợ và các ràng buộc cho `CartItem.java`, `Cart.java`. | Đã ghi nhận |
| v0.2 | 30/10/2026 | Ghi nhận các tương tác tiếp theo (lần 2 đến lần 13) và yêu cầu review của nhóm trưởng (chi tiết ở mục 8.1 và 8.2). Chưa có kết quả kiểm tra thực tế. | Đang thực hiện |
| v1.0 | [Ngày hoàn tất] | Tổng hợp kết quả cuối cùng sau khi hoàn thành việc rà soát và xác minh. | Chưa thực hiện |

*Lưu ý: Chỉ cập nhật phiên bản và trạng thái khi các hoạt động tương ứng thực sự diễn ra.*

### 8.1 Nhật ký tương tác chi tiết

| # | Ngày | Prompt (tóm tắt) | Phản hồi của AI | Quyết định / kết quả |
|---|---|---|---|---|
| 1 | 28/10/2026 | Giao task CartItem và Cart, ràng buộc riêng, code theo diagram, lưu audit log. | AI sinh code đầy đủ cho `CartItem.java`, `Cart.java` và bản audit log đầu tiên. | Đây là nguồn của code ban đầu (do AI sinh). |
| 2 | 28/10/2026 | Giải thích code CartItem. | AI giải thích từng phần của `CartItem`. | Chỉ để đọc hiểu, không đổi code. |
| 3 | 28/10/2026 | Không nhất thiết phải dùng throw new. | AI viết lại `CartItem` bỏ exception, chuyển sang kiểm tra bằng `if`. | Người thực hiện chấp nhận. |
| 4 | 28/10/2026 | Có nên dùng if else để tránh nhập sai trong CartItem không. | AI tư vấn giữ nguyên, validate chính ở `Cart`. | Chỉ tư vấn. |
| 5 | 28/10/2026 | Sửa cả Cart vì không dùng throw. | AI sửa constructor `Cart` bỏ exception. | Người thực hiện chấp nhận. |
| 6 | 30/10/2026 | Gửi video nhóm trưởng review; yêu cầu AI chỉ ra lỗi cần sửa, không xuất code, không bịa code. | AI xem video, tổng hợp yêu cầu và chỉ vị trí cần sửa trong `CartItem`, `Cart`. AI không xuất code. | Người thực hiện tự gõ tay chỉnh sửa. Kết quả: xác nhận. |
| 7 | 30/10/2026 | Hỏi nên sửa như thế nào cho hợp lý, không xuất code để tự gõ tay. | AI hướng dẫn thứ tự sửa `CartItem` rồi `Cart`, nguyên tắc từng bước và 5 tình huống tự kiểm tra. AI không xuất code. | Người thực hiện tự gõ tay. Kết quả: xác nhận. |
| 8 | 30/10/2026 | Yêu cầu tạo Claude Doc từ hướng dẫn ở lần 7; yêu cầu lưu audit log mỗi lần tương tác. | AI tạo Claude Doc "Hướng dẫn sửa CartItem và Cart theo review nhóm trưởng" (nội dung giống lần 7, không có code) và cập nhật audit log. | Tài liệu dùng để đối chiếu khi tự sửa. Audit log được cập nhật sau mỗi lần tương tác. |
| 9 | 30/10/2026 | Người thực hiện tự gõ khung `CartItem` và `Cart` theo diagram, nhờ AI đóng vai trò hướng dẫn logic (chỉ rõ hàm, file; giải thích thuật toán bằng lời), bắt đầu với `CartItem`. | AI chỉ ra các lỗi trong khung `CartItem` (getter, `setQuantity`, `addQuantity`, constructor) và hướng dẫn thuật toán từng hàm bằng lời. AI không xuất code. |  Quyết định: Người thực hiện tạm dừng tương tác, lưu lại bản log hiện tại và chờ hệ thống mở lại lúc 14:50 để tiếp tục hỏi luồng logic. |
| 10 | 30/10/2026 | Người thực hiện gửi bản `CartItem` tự gõ (đã chuẩn hóa mã sản phẩm, thêm điều kiện cho `setQuantity`, `addQuantity`) để AI rà soát. | AI chỉ ra các lỗi còn lại: thiếu dấu chấm phẩy, lỗi chính tả tên biến, khai báo trùng biến `amount`, điều kiện `addQuantity` sai chiều, getter còn phép gán, constructor chưa kiểm tra 1-1.000 và chưa `public`. AI không xuất code. | Người thực hiện tự tay sửa trực tiếp vào IDE các lỗi biên dịch và logic được chỉ ra. |
| 11 | 30/10/2026 | Người thực hiện gửi bản `CartItem` đã sửa lại để AI rà soát tiếp. | AI xác nhận các lỗi biên dịch, điều kiện `addQuantity` và getter đã được sửa đúng; chỉ ra còn thiếu kiểm tra 1-1.000 trong constructor và nhắc giữ dòng `package`. AI không xuất code. | Người thực hiện tự sửa. Kết quả: đã sửa. |
| 12 | 30/10/2026 | Người thực hiện gửi bản `CartItem` cuối cùng, nhờ AI quét lỗi lần cuối và chuẩn bị chuyển sang `Cart` (chờ lệnh). | AI rà code, biên dịch thử và chạy 14 tình huống kiểm tra trong môi trường riêng của AI (Java 21, bản sao code của người thực hiện): biên dịch thành công, 14/14 đạt. AI không xuất code. | `CartItem` ổn về logic. Người thực hiện vẫn cần tự biên dịch và chạy thử trên máy mình để xác nhận. |
| 13 | 30/10/2026 | Yêu cầu tiến hành làm `Cart.java` và giải thích chi tiết lý do dùng từng đoạn logic; lưu audit log. | AI hướng dẫn thứ tự sửa khung `Cart`, thuật toán từng hàm bằng lời và lý do thiết kế (xem mục 8.3). AI giữ nguyên ràng buộc không xuất code. | Người thực hiện tự gõ tay. Kết quả: xác nhận. |
| 14 | 30/10/2026 | Hỏi tại sao AI chọn phương án thiết kế này cho Cart.| AI giải thích lý do từng lựa chọn (bắt buộc theo yêu cầu vs suy luận của AI), các phương án thay thế. AI không xuất code. | Dùng làm cơ sở bảo vệ dự án. Các điểm suy luận vẫn cần chờ nhóm trưởng xác nhận.|
| 15 | 30/10/2026 | Yêu cầu phân tích ý nghĩa của việc AI chuyển sang hướng dẫn logic thay vì xuất code, và ý nghĩa thiết kế của Cart.java.| AI phân tích 4 ý nghĩa: Lập trình phòng thủ, Tính đóng gói (bảo vệ dữ liệu khỏi module khác), Quy tắc thuật toán, và Kỹ năng phân tích trade-off. AI không xuất code. | Người thực hiện đọc hiểu để rèn luyện tư duy kỹ sư phần mềm (Engineering Mindset), nắm vững bản chất để điền Audit Log minh bạch. |
| 16 | 30/10/2026 | (không có prompt code) | (Không có phản hồi từ AI) | Tạm dừng code "Cart.java" để tìm hiểu thêm các lệnh cần thiết. |
| 17 | 30/10/2026 | (không có prompt code) | (Không có phản hồi từ AI) | Chỉnh sửa code của CartItem.Java theo lệnh "throw new IllegalArgumentException" và validate do nhóm trưởng Nguyễn Văn Trung Kiên đề xuất và có ví dụ tham khảo. |
| 18 | 30/10/2026 | Tạm ngưng code Cart.java, bổ sung lệnh trên CartItem.java do nhóm trưởng yêu cầu. | (Không có phản hồi từ AI - Claude hết token) | Quyết định: Người thực hiện chuyển sang dùng GEMINI Plus (Anthropic) dự phòng để tiếp tục. |
| 19 | 30/10/2026 | Cung cấp toàn bộ Audit Log cho AI dự phòng. Yêu cầu đóng vai trò hướng dẫn logic để thêm IllegalArgumentException vào CartItem, tuyệt đối không xuất code, cập nhật Audit Log. | AI dự phòng phân tích luồng logic mới, đưa ra ví dụ về cú pháp Exception độc lập và liệt kê 3 vị trí cần chặn lỗi trong CartItem (Constructor, setQuantity, addQuantity). Cập nhật Audit log. Tuyệt đối không xuất code.| Người thực hiện đang tự gõ tay các đoạn chặn lỗi bằng Exception vào CartItem.java trên IDE. | Kết quả: Đang chờ người thực hiện code xong và biên dịch thử. |
| 20 | 30/10/2026 | Cung cấp video tin nhắn của nhóm trưởng yêu cầu dùng throw new IllegalArgumentException thay cho Silent Failure và áp dụng validate. Yêu cầu định hướng logic để tự code. | AI phân tích video, chỉ ra sự khác biệt giữa Silent Failure và Fail-fast. Cung cấp định hướng luồng kiểm tra điều kiện ngược (bắt lỗi) cho Constructor, setQuantity, và addQuantity để ném ngoại lệ theo đúng yêu cầu nhóm trưởng. Không xuất code. | Người thực hiện đọc hiểu định hướng logic, chuẩn bị tự gõ các điều kiện throw new IllegalArgumentException vào CartItem.java trên IDE. Kết quả: Đang thực hiện. |
| 21 | 30/10/2026 | Người thực hiện gửi bản code CartItem.java tự gõ (đã thêm IllegalArgumentException) để kiểm tra lỗi. Yêu cầu cập nhật Audit log. | AI dự phòng phát hiện 2 lỗi logic nghiêm trọng: nguy cơ NullPointerException ở Constructor (do dùng sai điều kiện != null) và lỗi logic gộp toán tử ở addQuantity kèm việc chưa tuân thủ Fail-fast. AI hướng dẫn cách sửa lỗi điều kiện, không xuất toàn bộ code.| Người thực hiện đọc hiểu các lỗi logic, tự sửa lại các điều kiện if trong CartItem.java trên IDE cho chính xác. Kết quả: Đang thực hiện rà soát lại. |
| 22 | 30/10/2026 | Người thực hiện gõ xong khung code Cart.java và gửi để AI rà soát lỗi. | AI phát hiện nhiều lỗi cú pháp ngăn cản biên dịch: gọi sai tên biến (items vs item), dư khoảng trắng ở toán tử (> =), dư dấu phẩy khi dùng từ khóa new, sai generic type, và logic removeItem bị gộp sai hoàn toàn. AI hướng dẫn chi tiết cách sửa từng dòng, tuyệt đối không xuất code.| Người thực hiện đọc hiểu, tự sửa các lỗi cú pháp (Syntax Error) trực tiếp trên IDE. Kết quả: Đang chờ biên dịch lại.|
| 23 | 30/10/2026 | Người thực hiện gửi lại code Cart.java sau khi đã tự fix cú pháp và đổi tên biến item thành items. Nhờ rà soát lại. | AI xác nhận các lỗi cú pháp trước đó đã được khắc phục hoàn toàn. Phát hiện 1 lỗi biên dịch nhỏ mới phát sinh trong hàm updateQuantity do khai báo sai tên biến cục bộ (items thay vì item). AI hướng dẫn cách sửa tên biến, tuyệt đối không xuất code.| Người thực hiện tự sửa lại tên biến cục bộ trong updateQuantity trên IDE. Kết quả: Đang hoàn thiện bước kiểm tra cú pháp cuối cùng.|
### 8.2 Yêu cầu review của nhóm trưởng (video ngày 30/10/2026)
1. Thêm `this.` khi truy cập field trong cả `CartItem` và `Cart` (getter, `getItems`, `isConfirmed`, `setConfirmed`, `getItemCount`, các chỗ dùng field khác).
2. `CartItem`: thêm trường hợp số lượng không được vượt quá 1.000.
3. Chuẩn hóa mã sản phẩm: sau `trim()` thêm chuyển chữ hoa, ở mọi nơi có `trim()`.
4. `Cart.addItem`: thêm trường hợp giỏ tối đa 20 vật phẩm, và kiểm tra tổng sau khi gộp có vượt quá 1.000 không.
5. Nhóm trưởng nhắc: tự code tay, không copy-paste; ghi hết vào audit log để xác định nguồn code.

### 8.3 Giải thích lý do thiết kế `Cart` (AI hướng dẫn, 30/10/2026)

| Hàm / phần | Quyết định | Lý do |
|---|---|---|
| `import` | Cần `LocalDate`, `Map`, `LinkedHashMap`, `Collections` | Khung dùng các kiểu này; thiếu `import` thì không biên dịch được. `LinkedHashMap` giữ thứ tự thêm sản phẩm, `Collections` dùng để trả về view chỉ đọc. |
| Constructor | `saleDate` null thì dùng ngày hiện tại; `items` null thì tạo map rỗng; có map thì copy sang map mới | Tránh `NullPointerException` về sau. Copy để bên ngoài sửa map gốc không làm hỏng giỏ hàng. |
| Chuẩn hóa mã sản phẩm | `trim()` rồi `toUpperCase()` ở `addItem`, `updateQuantity`, `removeItem`, kèm kiểm tra null trước | `CartItem` lưu mã đã chuẩn hóa nên key trong map là chữ hoa; tra cứu bằng chữ thường sẽ không thấy key. Null thì `trim()` gây lỗi nên phải kiểm tra trước. Theo yêu cầu nhóm trưởng. |
| `addItem` | Kiểm tra mọi điều kiện trước, rồi mới đổi dữ liệu; sai thì trả `false` | `CartItem.addQuantity` và `setQuantity` im lặng bỏ qua giá trị sai, nên `Cart` phải tự kiểm tra thì mới trả đúng `true`/`false` cho `SaleService`. |
| `addItem`, số lượng | `quantity` phải từ 1 đến 1.000 trước khi làm tiếp | Chặn giá trị sai sớm; giúp phép cộng tổng sau khi gộp không bị tràn số. |
| `addItem`, sản phẩm mới | Nếu giỏ đã đủ 20 dòng thì trả `false` | Yêu cầu giỏ tối đa 20 dòng sản phẩm khác nhau (cần nhóm trưởng xác nhận cách hiểu). |
| `addItem`, sản phẩm đã có | Tính tổng cũ cộng mới, vượt 1.000 thì trả `false`, không vượt thì gọi `addQuantity` | Yêu cầu: trùng mã thì gộp dòng, giới hạn kiểm tra trên tổng sau khi gộp. Giới hạn 20 dòng không áp dụng ở nhánh này vì số dòng không tăng. |
| `updateQuantity` | Chuẩn hóa id, tìm item, không có thì `false`; số lượng mới ngoài 1-1.000 thì `false`; hợp lệ mới gọi `setQuantity` | Cùng lý do `addItem`. Giới hạn 1.000 ở đây là suy luận cho nhất quán, cần nhóm trưởng xác nhận. |
| `removeItem` | Chuẩn hóa id rồi xóa; trả `true` chỉ khi thực sự xóa được | Cần chuẩn hóa để tìm đúng key; trả giá trị đúng để nơi gọi biết có xóa hay không. |
| Giỏ đã `confirmed` | Từ chối thêm, sửa, xóa | Quyết định thiết kế (không có trong diagram và yêu cầu nhóm trưởng): giỏ đã xác nhận không nên bị thay đổi. Cần nhóm trưởng xác nhận. |
| `getItems()` | Trả về map chỉ đọc | Để `SaleService` chỉ đọc giỏ, không sửa trực tiếp và qua mặt các kiểm tra của `Cart`. |
| `getItemCount()` | Trả về số dòng sản phẩm | Cách hiểu của AI, cần nhóm xác nhận. |
| Getter, `setConfirmed` | Dùng `this.` và đúng kiểu trả về | Yêu cầu nhóm trưởng; `setConfirmed` là `void` nên chỉ gán, không `return`. |

## 9. Trách nhiệm của người thực hiện

Người thực hiện chịu trách nhiệm xem xét code do Claude AI đề xuất, đối chiếu với yêu cầu của dự án, thực hiện hoặc xác nhận các bước kiểm thử và quyết định nội dung được sử dụng trong sản phẩm cuối cùng.

Audit log này được cập nhật trong quá trình làm việc nhằm bảo đảm tính minh bạch về việc sử dụng AI. Những nội dung chưa được kiểm tra hoặc xác nhận sẽ tiếp tục được ghi nhận là đang chờ xử lý, thay vì được xem là đã hoàn thành.