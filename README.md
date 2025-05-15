Lv 0 API 명세 및 ERD 작성 - API 명세서

## 일정 생성
| 항목         | 내용                           |
|--------------|--------------------------------|
| **Method**   | `POST`                         |
| **URL**      | `{{url}}/api/schedule`         |
| **설명**     | 새로운 일정을 등록합니다.       |
| **Request Body** | JSON (application/json)     |

### 자료형
    member_name | String
    title | String
    content | String

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
---