# controller 패키지의 PlanController
***
<어떤 형식으로 받아서 어떻게 처리해서 Service 에게 전달할 것인지>

* @RestController 👉 @Controller 의 역할과 @ResponseBody 인 JSON 데이터 반환하는 메서드

* @RequestMapping 👉 Mapping 안 공통으로 쓸 URL 설정

* @RequestBody 👉 요청 데이터(JSON 이든 뭐든)를 PlanRequestDto 객체로 바꿔주는 역할, 내부적으로 Jackson 라이브러리 쓴다

      private final PlanService planService;
* planService 필드 선언


    public PlanController(PlanService planService) {
    this.planService = planService;
    }
* planService 생성자를 통한 초기화


    @PostMapping("/plans")
    public PlanResponseDto createPlan(@RequestBody PlanRequestDto requestDto){
    return planService.createPlan(requestDto);
    }
일정 작성(Create) 를 위한 @PostMapping
Json 을 PlanRequestDto 객체로 바꿔서 매개변수로 쓰는데 이 매개변수를 받아서
planService 에 requestDto 를 받아 반환

## service 패키지의 PlanService
____
<요청 Dto, entity 를 통해 응답 Dto 에게 전달하기 위해>

* 요청 데이터를 entity 를 가져와서 savePlan 에 넣고 응답 dto 에게 전달


## dto 패키지의 ResponseDto 와 RequestDto
____
RequestDto 클라이언트 👉 서버, ResponseDto 서버 👉 클라이언트

클라이언트로부터 title, contents, user, password 요청받고

서버로부터 id, title, contents, user, createdAt 받아서 클라이언트에게 주는 역할


## entity 패키지의 Plan
* @Entity 👉 Plan 클래스를 plan 테이블과 매핑(대응)
* @Table(name ="plan") 👉 테이블 이름 plan
* @NoArgsConstructor 👉 기본 생성자 호출
* @Id 👉 id 필드를 기본 키로 설정
* @GeneratedValue(strategy = GenerationType.IDENTITY) 👉 자동으로 값 생성


## repository 패키지의 PlanRepository
* JpaRepository<Plan, Long> Plan 객체와 id 를 받아 DB 관리