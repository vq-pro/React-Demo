@Service
Feature: Backend demo

  Scenario: Get Greeting
    When we ask for a greeting for "Patrick" [GET "/greeting/{name}"]
    Then we get a greeting message
      """
        {
          "content": "Hello Patrick!"
        }
      """
