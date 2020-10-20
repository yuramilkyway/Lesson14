package ru.innopolis.ejb;

import javax.ejb.Stateless;
import java.util.*;

/**
 * Создаем бин, для получения системных переменных
 */
@Stateless
public class EnvironmentVarBean {
    /**
     * Получаем список всех системных переменных и сорируем по алфавиту.
     * @return Отсортированный лист с системными переменными.
     */
    public Collection<Map.Entry<String, String>> getAllVariables() {
        Set<Map.Entry<String, String>> set = System.getenv().entrySet();
        List<Map.Entry<String, String>> list = new ArrayList<>(set);
        list.sort(Map.Entry.comparingByKey());
        return list;
    }

    /**
     * Получаем значение у заданной системной переменной.
     * @param name Имя системной переменной.
     * @return Значение системной переменной.
     */
    public String getValueVariable(String name) {
        return System.getenv(name);
    }
}
