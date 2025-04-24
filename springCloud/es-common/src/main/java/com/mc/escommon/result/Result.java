//package com.mc.common;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Result {
//
//    private static final String SUCCESS = "0";
//    private static final String ERROR = "-1";
//
//    private String code;
//    private String msg;
//    private Object data;
//
//    public static Result success() {
//        Result result = new Result();
//        result.setCode(SUCCESS);
//        return result;
//    }
//
//    public static Result success(Object data) {
//        Result result = new Result();
//        result.setCode(SUCCESS);
//        result.setData(data);
//        return result;
//    }
//
//    public static Result error(String msg) {
//        Result result = new Result();
//        result.setCode(ERROR);
//        result.setMsg(msg);
//        return result;
//    }
//}

package com.mc.escommon.result;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
    @Schema(description = "返回代码")
    private String code;
    @Schema(description = "返回信息")
    private String msg;
    @Schema(description = "返回数据")
    private Object data;

    private Result(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public static Result success() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    public static Result success(Object data) {
        Result tResult = new Result (data);
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    public static Result error() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
        tResult.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
        return tResult;
    }

    public static Result error(String code, String msg) {
        Result tResult = new Result();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    public static Result error(ResultCodeEnum resultCodeEnum) {
        Result tResult = new Result();
        tResult.setCode(resultCodeEnum.code);
        tResult.setMsg(resultCodeEnum.msg);
        return tResult;
    }

}
