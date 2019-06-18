package compiler;

/**
 * 反编译可知，最后生成的还是类，final类型
 * Compiled from "EnumClass.java"
 * public final class compiler.EnumClass extends java.lang.Enum<compiler.EnumClass> {
 *   public static final compiler.EnumClass SPRING;
 *
 *   public static final compiler.EnumClass SUMMER;
 *
 *   public static compiler.EnumClass[] values();
 *     Code:
 *        0: getstatic     #1                  // Field $VALUES:[Lcompiler/EnumClass;
 *        3: invokevirtual #2                  // Method "[Lcompiler/EnumClass;".clone:()Ljava/lan
 * g/Object;
 *        6: checkcast     #3                  // class "[Lcompiler/EnumClass;"
 *        9: areturn
 *
 *   public static compiler.EnumClass valueOf(java.lang.String);
 *     Code:
 *        0: ldc           #4                  // class compiler/EnumClass
 *        2: aload_0
 *        3: invokestatic  #5                  // Method java/lang/Enum.valueOf:(Ljava/lang/Class;
 * Ljava/lang/String;)Ljava/lang/Enum;
 *        6: checkcast     #4                  // class compiler/EnumClass
 *        9: areturn
 *
 *   static {};
 *     Code:
 *        0: new           #4                  // class compiler/EnumClass
 *        3: dup
 *        4: ldc           #7                  // String SPRING
 *        6: iconst_0
 *        7: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
 *       10: putstatic     #9                  // Field SPRING:Lcompiler/EnumClass;
 *       13: new           #4                  // class compiler/EnumClass
 *       16: dup
 *       17: ldc           #10                 // String SUMMER
 *       19: iconst_1
 *       20: invokespecial #8                  // Method "<init>":(Ljava/lang/String;I)V
 *       23: putstatic     #11                 // Field SUMMER:Lcompiler/EnumClass;
 *       26: iconst_2
 *       27: anewarray     #4                  // class compiler/EnumClass
 *       30: dup
 *       31: iconst_0
 *       32: getstatic     #9                  // Field SPRING:Lcompiler/EnumClass;
 *       35: aastore
 *       36: dup
 *       37: iconst_1
 *       38: getstatic     #11                 // Field SUMMER:Lcompiler/EnumClass;
 *       41: aastore
 *       42: putstatic     #1                  // Field $VALUES:[Lcompiler/EnumClass;
 *       45: return
 * }
 * @author LiCola
 * @date 2019-06-18
 */
public enum  EnumClass {

  SPRING, SUMMER;
}
