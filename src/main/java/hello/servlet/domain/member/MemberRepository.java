package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려

public class MemberRepository {
    //static으로 생성 되었기 때문에 new MemberRepository가 아무리 많이 생성되어도 아래는 1개씩만 생성됨.
    private static Map<Long,Member> store =new HashMap<>();
    private static long sequence=0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    //아무나 생성 못하게 생성자 막아줌
    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());     //store에 있는 모든 값들을 꺼내서 ArrayList에 넣어준다.
    }
    public void clearStore(){
        store.clear();
    }
}
