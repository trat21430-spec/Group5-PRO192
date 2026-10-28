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

*(Nhật ký sẽ tiếp tục được cập nhật khi có các lần tương tác AI tiếp theo)*
