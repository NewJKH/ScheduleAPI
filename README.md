Lv 0 API 명세 및 ERD 작성 - API 명세서

## 일정 생성
| 항목         | 내용                           |
|--------------|--------------------------------|
| **Method**   | `POST`                         |
| **URL**      | `{{url}}/api/schedule`         |
| **설명**     | 새로운 일정을 등록합니다.       |
| **Request Body** | JSON (application/json)     |

### 🔸 자료형
| 필드명        | 타입     | 필수 | 설명         |
|---------------|----------|----|--------------|
| member_name   | String   | O  | 작성자 이름   |
| title         | String   | O  | 일정 제목     |
| content       | String   | O  | 일정 내용     |

### 요청
```json
{
  "member_name": "홍길동",
  "title": "과제",
  "content": "스프링 복습"
}
```

### 응답
```json
{
  "member_name": "홍길동",
  "title": "과제",
  "content": "스프링 복습",
  "created_at": "2025-04-01",
  "modified_at": "2025-04-01"
}
```
### 상태코드
| 코드  | 설명              |
| --- | --------------- |
| 200 | 일정 생성 성공        |
| 400 | 필드 누락 또는 유효성 실패 |
| 500 | 서버 내부 오류        |

---