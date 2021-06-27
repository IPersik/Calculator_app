package com.example.alculator_app;

import androidx.constraintlayout.solver.state.State;

public class CalculatorModel {

    private int first_number;
    private int second_number;
    private StringBuilder input_string = new StringBuilder();
    private int selected_action;
    private State state;

    private enum State {
        first_number_input,
        operation_selected,
        second_number_input,
        show_result
    }

    public CalculatorModel() {
        state = State.first_number_input;
    }

    public void onNumPressed(int buttonId) {

        if (state == State.show_result) {
            state = State.first_number_input;
            input_string.setLength(0);
        }

        if (state == State.operation_selected) {
            state = State.second_number_input;
            input_string.setLength(0);
        }
        if (input_string.length() < 9) {
            switch (buttonId) {
                case R.id.zero:
                    if (input_string.length() != 0) {
                        input_string.append("0");
                    }
                    break;
                case R.id.one:
                    input_string.append("1");
                    break;
                case R.id.two:
                    input_string.append("2");
                    break;
                case R.id.three:
                    input_string.append("3");
                    break;
                case R.id.four:
                    input_string.append("4");
                    break;
                case R.id.five:
                    input_string.append("5");
                    break;
                case R.id.six:
                    input_string.append("6");
                    break;
                case R.id.seven:
                    input_string.append("7");
                    break;
                case R.id.eight:
                    input_string.append("8");
                    break;
                case R.id.nine:
                    input_string.append("9");
                    break;
            }
        }
    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equals && state == State.second_number_input && input_string.length() > 0) {
            second_number = Integer.parseInt(input_string.toString());
            state = State.show_result;
            input_string.setLength(0);
            switch ((int) selected_action) {
                case R.id.plus:
                    input_string.append(first_number + second_number);
                    break;
                case R.id.minus:
                    input_string.append(first_number - second_number);
                    break;
                case R.id.multiply:
                    input_string.append(first_number * second_number);
                    break;
                case R.id.division:
                    input_string.append(first_number / second_number);
                    break;
            }
        } else if (input_string.length() > 0 && state == State.first_number_input) {
            first_number = Integer.parseInt(input_string.toString());
            state = State.operation_selected;
            selected_action = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return input_string.toString();
            case operation_selected:
                return str.append(first_number).append(' ')
                        .append(getOperationChar())
                        .toString();
            case second_number_input:
                return str.append(first_number).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(input_string)
                        .toString();
            case show_result:
                return str.append(first_number).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(second_number)
                        .append(" = ")
                        .append(input_string.toString())
                        .toString();
        }
    }

    private char getOperationChar() {
        switch ((int) selected_action) {
            case R.id.plus:
                return '+';
            case R.id.minus:
                return '-';
            case R.id.multiply:
                return '*';
            case R.id.division:
            default:
                return '/';

        }
    }

    public void reset() {
        state = State.first_number_input;
        input_string.setLength(0);
    }

}
