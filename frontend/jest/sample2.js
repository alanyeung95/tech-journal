import React from "react";
import renderer from "react-test-renderer";
import { Provider } from "react-redux";
import { CookiesProvider } from "react-cookie";

import Store from "../../store";
import ComponentB from "./componentB.presenter";

const mockData = {
    data: {
        name: "test_data",
        _id: "87654321",
    },
};

const storeValue = { ...mockData };

const mockProps = {
    _source: { ...mockData },
};

jest.mock("../../api", () => {
    return {
        data: {
            getData: jest.fn().mockImplementation(() => {
                return new Promise((resolve) => {
                    resolve(mockData.name);
                });
            }),
        },
        otherData: {
            getOtherData: jest.fn().mockImplementation(() => {
                return new Promise((resolve) => {
                    resolve([]);
                });
            }),
        },
        user: {
            getUsers: jest.fn().mockImplementation(() => {
                return [
                    { _id: "123456", name: "Alan Yeung" },
                ];
            }),
        },
    };
});

beforeEach(() => {
    Object.defineProperty(document, "cookie", {
        writable: true,
        value: "currentUserId=1234567",
    });
});

it("renders correctly", async () => {
    const tree = renderer.create(
        <CookiesProvider>
            <Provider value={storeValue.project} store={Store}>
                <ComponentB _source={mockProps.data} />
            </Provider>
        </CookiesProvider>
    );

    Store.dispatch({
        type: "SELECT_DATA",
        payload: {
            project: storeValue.data,
        },
    });

    const flushPromises = () => new Promise(setImmediate);
    await flushPromises();

    expect(tree.toJSON()).toMatchSnapshot();
});
