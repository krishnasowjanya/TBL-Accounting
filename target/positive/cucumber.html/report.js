$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/crmApplicationScenarios/Approve_Organization_Taxpayer.feature");
formatter.feature({
  "line": 1,
  "name": "Organization Taxpayer Approve",
  "description": "",
  "id": "organization-taxpayer-approve",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 2,
  "name": "Organization Taxpayer Approve Scenario",
  "description": "",
  "id": "organization-taxpayer-approve;organization-taxpayer-approve-scenario",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "Open CRM URL",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "Close Popup Window",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Click on Case management Sub module",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "Goto Queues",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "switch to frame",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "Select SearchResult Dropdown element",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Selecte Queues Dropdown element",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Enter the Reference number \"\u003cReferenceNumber\u003e\" in search box.",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Click on Serch icon",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Click selected Reference Number",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Pick the selected Reference number",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Click on Case management Sub module",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Goto Dashboard",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Enter Reference number in search box \"\u003cReferenceNumber\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "Click Dashboard serch icon",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Click on appeard Reference number",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Click on Open Case Record link",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "Click on Orgnization NextStage button",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "Goto view Organization AttachmentDetails screen",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "Download Organization Attachments",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "Select Organization Approval outcome dropdown value",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "Click on Save button",
  "keyword": "Then "
});
formatter.step({
  "line": 25,
  "name": "Verify the String \"\u003cRead\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Click on Taxpayer name",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Get the TIN Number",
  "keyword": "Then "
});
formatter.examples({
  "line": 29,
  "name": "",
  "description": "",
  "id": "organization-taxpayer-approve;organization-taxpayer-approve-scenario;",
  "rows": [
    {
      "cells": [
        "username",
        "password",
        "browser",
        "ReferenceNumber",
        "Approve",
        "Read"
      ],
      "line": 30,
      "id": "organization-taxpayer-approve;organization-taxpayer-approve-scenario;;1"
    },
    {
      "cells": [
        "tripscrmuser11",
        "Passw0rd",
        "chrome",
        "*ARN/00020520/2019",
        "Approve",
        "Read only"
      ],
      "line": 31,
      "id": "organization-taxpayer-approve;organization-taxpayer-approve-scenario;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 17854700,
  "status": "passed"
});
formatter.before({
  "duration": 16961747200,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Organization Taxpayer Approve Scenario",
  "description": "",
  "id": "organization-taxpayer-approve;organization-taxpayer-approve-scenario;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "Open CRM URL",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "Close Popup Window",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Click on Case management Sub module",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "Goto Queues",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "switch to frame",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "Select SearchResult Dropdown element",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Selecte Queues Dropdown element",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Enter the Reference number \"*ARN/00020520/2019\" in search box.",
  "matchedColumns": [
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Click on Serch icon",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Click selected Reference Number",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Pick the selected Reference number",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Click on Case management Sub module",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Goto Dashboard",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Enter Reference number in search box \"*ARN/00020520/2019\"",
  "matchedColumns": [
    3
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "Click Dashboard serch icon",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Click on appeard Reference number",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Click on Open Case Record link",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "Click on Orgnization NextStage button",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "Goto view Organization AttachmentDetails screen",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "Download Organization Attachments",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "Select Organization Approval outcome dropdown value",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "Click on Save button",
  "keyword": "Then "
});
formatter.step({
  "line": 25,
  "name": "Verify the String \"Read only\"",
  "matchedColumns": [
    5
  ],
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "Click on Taxpayer name",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "Get the TIN Number",
  "keyword": "Then "
});
formatter.match({
  "location": "AStepDefnition.open_CRM_URL()"
});
formatter.result({
  "duration": 34650673300,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.close_Popup_Window()"
});
formatter.result({
  "duration": 725518000,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.click_on_Case_management_Sub_module()"
});
formatter.result({
  "duration": 336896400,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.goto_Queues()"
});
formatter.result({
  "duration": 258778000,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.switch_to_frame()"
});
formatter.result({
  "duration": 8076266200,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.select_SearchResult_Dropdown_element()"
});
formatter.result({
  "duration": 1111280900,
  "status": "passed"
});
formatter.match({
  "location": "AStepDefnition.selecte_Queues_Dropdown_element()"
});
formatter.result({
  "duration": 5485768800,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "location": "AStepDefnition.click_on_Serch_icon()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_selected_Reference_Number()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.pick_the_selected_Reference_number()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_Case_management_Sub_module()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.goto_Dashboard()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "location": "AStepDefnition.click_Dashboard_serch_icon()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_appeard_Reference_number()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_Open_Case_Record_link()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_Orgnization_NextStage_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.goto_view_Organization_AttachmentDetails_screen()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.download_Organization_Attachments()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.select_Organization_Approval_outcome_dropdown_value_to_Approve()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_Save_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Read only",
      "offset": 19
    }
  ],
  "location": "AStepDefnition.verify_the_String(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.click_on_Taxpayer_name()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "AStepDefnition.get_the_TIN_Number()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 172800,
  "status": "passed"
});
formatter.after({
  "duration": 346228200,
  "status": "passed"
});
formatter.after({
  "duration": 481258500,
  "status": "passed"
});
});