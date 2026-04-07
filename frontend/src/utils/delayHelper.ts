// Delay for API call
const delay = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));
export const withDelay = async <T>(promise: Promise<T>): Promise<T> => {
    const [result] = await Promise.all([promise, delay(2000)])
    return result;
}