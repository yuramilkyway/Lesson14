package ru.innopolis.Servler;

import ru.innopolis.ejb.EnvironmentVarBean;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 * Rest сервлет возвращающий переменные среды.
 */
public class EnvironmentVarsRest extends HttpServlet {

    @EJB
    private EnvironmentVarBean environmentVarBean;

    /**
     * Обрабатываем запрос. Если запрос пустой, то вызываем метод для вывода всех переменных среды.
     * Если нет, то вызываем метод для получение переменной среды по имени.
     * @param req Объект для хранения запроса.
     * @param resp Объект для хранения ответа.
     * @throws IOException Передаем серверу право обработать исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        PrintWriter writer = resp.getWriter();

        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            doGetSingle(writer, pathInfo.substring(1));
        } else {
            doGetAll(writer);
        }
    }

    /**
     * Получает поток и записывает в него переменные среды.
     * @param writer Поток для записи.
     */
    private void doGetAll(PrintWriter writer) {
        Collection<Map.Entry<String, String>> allVariables = environmentVarBean.getAllVariables();

        for (Map.Entry<String, String> variable : allVariables) {
            writer.println(variable);
        }
    }

    /**
     * Получает поток и записывает в него переменную среды по имени name.
     * @param writer Поток для записи.
     * @param name Название переменной, значение которой хотим получить.
     */
    private void doGetSingle(PrintWriter writer, String name) {
        writer.println(environmentVarBean.getValueVariable(name));
    }
}
