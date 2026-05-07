import { useState, useRef } from "react";
import API from "../services/api";
import ReactPlayer from "react-player";

const TimestampSearch = ({ uploadedFile }) => {

    const [topic, setTopic] = useState("");

    const [timestampData, setTimestampData] = useState(null);

    const [loading, setLoading] = useState(false);

    const playerRef = useRef(null);

    const handleSearch = async () => {

        if (!uploadedFile || !topic) return;

        try {

            setLoading(true);

            const response = await API.get(
                `/timestamps/search?fileId=${uploadedFile.id}&topic=${topic}`
            );

            setTimestampData(response.data);

        } catch (error) {

            console.error(error);

            alert("Timestamp search failed");
        } finally {

            setLoading(false);
        }
    };

    const handlePlay = () => {

        if (
            playerRef.current &&
            timestampData?.startTime !== undefined
        ) {

            playerRef.current.seekTo(
                timestampData.startTime,
                "seconds"
            );
        }
    };

    if (
        !uploadedFile ||
        !uploadedFile.fileType.startsWith("audio") &&
        !uploadedFile.fileType.startsWith("video")
    ) {
        return null;
    }

    return (
        <div className="bg-white p-6 rounded-xl shadow-md mt-6">

            <h2 className="text-2xl font-bold mb-4">
                Timestamp Search
            </h2>

            <input
                type="text"
                value={topic}
                onChange={(e) => setTopic(e.target.value)}
                placeholder="Search topic..."
                
                className="w-full border border-gray-300 rounded-xl p-3 my-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <button
                onClick={handleSearch}
                
                className="bg-purple-600 hover:bg-purple-700 transition text-white px-4 py-2 rounded-xl font-semibold"
            >
                {
                    loading
                        ? "Searching..."
                        : "Find Timestamp"
                }
            </button>

            {
                timestampData && (
                    <div className="mt-6">

                        <p>
                            <strong>Start:</strong>
                            {" "}
                            {timestampData.startTime}s
                        </p>

                        <p>
                            <strong>End:</strong>
                            {" "}
                            {timestampData.endTime}s
                        </p>

                        <p className="mt-2">
                            <strong>Transcript:</strong>
                            {" "}
                            {timestampData.text}
                        </p>

                        <button
                            onClick={handlePlay}
                            
                            className="bg-red-600 hover:bg-red-700 transition text-white px-4 py-2 mt-2 rounded-xl font-semibold"
                        >
                            Play From Timestamp
                        </button>

                        <div className="mt-6">

                            <ReactPlayer
                                ref={playerRef}
                                url={`http://localhost:8080/${uploadedFile.filePath}`}
                                controls
                                width="100%"
                                height="70px"
                            />

                        </div>

                    </div>
                )
            }

        </div>
    );
};

export default TimestampSearch;