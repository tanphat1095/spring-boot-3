package vn.phat.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import vn.phat.constant.AppConstant;
import vn.phat.dto.BaseCondition;
import vn.phat.dto.PageWrapper;
import vn.phat.entites.EntityAbstract;
import vn.phat.res.BaseRes;
import vn.phat.services.BaseService;

import java.io.Serializable;
import java.util.Locale;


/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Slf4j
public abstract class BaseRest<I extends Serializable, A extends  Serializable, U extends Enum<U>, C extends BaseCondition<U>,E extends EntityAbstract<I,A>> {

    protected BaseService<U,I,A,C,E> service;

    abstract <S extends BaseService<U,I,A,C,E>> void setService(S baseService);
    abstract <V extends Validator> V getValidator();
    abstract Class<E> getClassEntity();

    private static final String PAGE_DEFAULT = "1";
    private static final String PAGE_SIZE_DEFAULT = "20";

    @PostMapping(name = AppConstant.SEARCH)
    BaseRes<PageWrapper<E>> search(@RequestBody C condition
            , @RequestParam(name= AppConstant.PAGE, required = false, defaultValue = PAGE_DEFAULT) int page
            , @RequestParam(name = AppConstant.PAGE_SIZE, required = false, defaultValue = PAGE_SIZE_DEFAULT) int pageSize){
        long start = System.currentTimeMillis();
        return new BaseRes<>(service.search(condition, page, pageSize), start);
    }

    @GetMapping(value = AppConstant.GET_ID)
    BaseRes<E> getData(@PathVariable(AppConstant.ID) I id){
        long start = System.currentTimeMillis();
        return new BaseRes<>(service.findOne(id),start);
    }

    @PutMapping(value = AppConstant.SAVE)
    BaseRes<E> save(@Valid @RequestBody E data, BindingResult result, HttpServletRequest request, HttpServletResponse response, Locale locale){
        long start = System.currentTimeMillis();
        try {
            if(getValidator() != null)
                getValidator().validate(data, result);
            if(result.hasErrors()){
                return new BaseRes<>(null, start);
            }
            return new BaseRes<>(service.save(data), start);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return new BaseRes<>(null, start);
    }

    @DeleteMapping(value = AppConstant.DELETE_ID)
    BaseRes<Boolean> delete(@PathVariable(AppConstant.ID) I id){
        long start = System.currentTimeMillis();
        try {
            service.delete(id);
            return new BaseRes<>(true, start);
        } catch (Exception e) {
            return new BaseRes<>(null, start);
        }
    }

}
