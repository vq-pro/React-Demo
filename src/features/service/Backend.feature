@Service
Feature: Backend demo

#  FIXME0 Add security

  @Ignore
  Scenario: Get Greeting
    When we ask for a greeting for "Patrick" [GET "/greeting/{name}"]
    Then we get a greeting message
      """
        {
          "content": "Hello Patrick!"
        }
      """

  Scenario: Get Greeting - Without login
    Given we are not logged in
    When we ask for a greeting for "Patrick" [GET "/greeting/{name}"]
    Then we should get a 401 error
