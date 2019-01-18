@Service
Feature: Backend demo

  @Ignore
  @WIP
  Scenario: Getting Greeting
    When we ask for a greeting for "Patrick" [GET "/greeting"]
    Then we get a greeting message
      """
        {
          content: "Hello Patrick!"
        }
      """
