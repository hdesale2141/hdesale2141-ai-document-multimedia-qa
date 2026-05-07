import { useState } from "react";
import API from "../services/api";

const ChatSection = ({ uploadedFile }) => {

    const [question, setQuestion] = useState("");

    const [answer, setAnswer] = useState("");

    const [loading, setLoading] = useState(false);

    const handleAskQuestion = async () => {

        if (!question || !uploadedFile) return;

        try {

            setLoading(true);

            const response = await API.post(
                "/chat/ask",
                {
                    fileId: uploadedFile.id,
                    question: question
                }
            );

            setAnswer(response.data.answer);

        } catch (error) {

            console.error(error);

            setAnswer("Failed to get AI response.");
        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="bg-white p-6 rounded-xl shadow-md mt-6">

            <h2 className="text-2xl font-bold mb-4">
                AI Chat
            </h2>

            <textarea
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                placeholder="Ask questions about uploaded content..."
                
                className="w-full border border-gray-300 rounded-xl p-3 my-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <button
                onClick={handleAskQuestion}
                
                className="bg-green-600 hover:bg-green-700 transition text-white px-5 py-3 rounded-xl font-semibold"
            >
                {
                    loading
                        ? "Thinking..."
                        : "Ask AI"
                }
            </button>

            {
                answer && (
                    <div className="mt-6 bg-gray-100 p-4 rounded-lg">

                        <h3 className="font-bold text-lg mb-2">
                            AI Response
                        </h3>

                        <p className="text-gray-700 whitespace-pre-wrap">
                            {answer}
                        </p>

                    </div>
                )
            }

        </div>
    );
};

export default ChatSection;