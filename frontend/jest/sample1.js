import React from "react";
import renderer from "react-test-renderer";
import { Provider } from "react-redux";
import { CookiesProvider } from "react-cookie";

import Store from "../../store";
import ComponentA from "./componentA.presenter";

jest.mock("../../api", () => {
    return {
        data: {
            getDatas: jest.fn().mockImplementation(() => {
                return [
                    {
                        name: "test_data_1",
                        _id: "6200eec5601f3e811ebb4d75",
                    },
                ];
            }),
        },
    };
});

beforeEach(() => {
    Object.defineProperty(document, "cookie", {
        writable: true,
        value: 'user={"_id":"123456789","name":"Alan Yeung","username":"alan.yeung"}',
    });
});

it("renders correctly", async () => {
    const tree = renderer.create(
        <ComponentA>
            <Provider store={Store}>
                <ProjectList />
            </Provider>
        </ComponentA>
    );

    const flushPromises = () => new Promise(setImmediate);
    await flushPromises();

    expect(tree.toJSON()).toMatchSnapshot();
});
