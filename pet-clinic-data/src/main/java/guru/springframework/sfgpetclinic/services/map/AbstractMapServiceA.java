package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.modules.BaseEntity;

import java.util.*;

public abstract class AbstractMapServiceA<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if(object !=null){
            if(((BaseEntity)object).getId()==null){
                ((BaseEntity)object).setId(getNextId());
            }

            map.put(getNextId(), object);
        } else{
            throw new RuntimeException("Object cannot be null.");
        }
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        Iterator<Map.Entry<Long, T>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Long, T> obj = iterator.next();
            if(object.equals(obj)){
                iterator.remove();
            }
        }
    }

    private Long getNextId(){
        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e){
            nextId = 1L;
        }
        return nextId;
    }

}
