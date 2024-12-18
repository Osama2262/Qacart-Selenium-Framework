package Testcases;

import API.RegisterAPI;
import API.TaskAPI;
import Base.BaseTest;
import Pages.NewToDoPage;
import Pages.ToDoPage;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
@Feature("TODO Features")

public class ToDoTest extends BaseTest {

    @Test(description = " User are able to add a new TODO card ")
    public void AddNewTODo() {

        RegisterAPI registerAPI =new RegisterAPI();
        registerAPI.Register();
        TaskAPI taskAPI = new TaskAPI();
        taskAPI.addTask(registerAPI.getAccessToken());
        NewToDoPage newToDoPage =new NewToDoPage(getDriver());
        newToDoPage.load_NewToDoPage();
        injectCookies(registerAPI.getRestAssuredCookies());

        String actual_result =
                newToDoPage.load_NewToDoPage()
                .clickOnButton()
                .enterTodoName("Test123")
                .clickToCreate().getTodoName();

        Assert.assertEquals(actual_result, "Test123");
    }

    @Test (description = " User are able to delete the TODO card successfully")
    public void ableToDeleteToDo(){

        NewToDoPage newToDoPage = new NewToDoPage(getDriver());
        RegisterAPI registerAPI = new RegisterAPI();
        ToDoPage toDoPage = new ToDoPage(getDriver());

        registerAPI.Register();
        TaskAPI taskAPI = new TaskAPI();
        taskAPI.addTask(registerAPI.getAccessToken());

        newToDoPage.load_NewToDoPage();
        injectCookies(registerAPI.getRestAssuredCookies());
        newToDoPage.load_NewToDoPage()
                .clickOnButton()
                .enterTodoName("Hello")
                .clickToCreate();
                toDoPage.deleteToDo();
        Assert.assertTrue(toDoPage.NoAvailableTodos());
    }


}
